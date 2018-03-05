/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package org.apache.skywalking.apm.plugin.jdbc.mssql.define;

import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.matcher.ElementMatcher;
import org.apache.skywalking.apm.agent.core.plugin.interceptor.ConstructorInterceptPoint;
import org.apache.skywalking.apm.agent.core.plugin.interceptor.InstanceMethodsInterceptPoint;
import org.apache.skywalking.apm.agent.core.plugin.interceptor.enhance.ClassInstanceMethodsEnhancePluginDefine;
import org.apache.skywalking.apm.agent.core.plugin.match.ClassMatch;
import org.apache.skywalking.apm.agent.core.plugin.match.MultiClassNameMatch;

import static net.bytebuddy.matcher.ElementMatchers.named;
import static net.bytebuddy.matcher.ElementMatchers.takesArguments;
import static org.apache.skywalking.apm.plugin.jdbc.define.Constants.CLOSE_METHOD_NAME;
import static org.apache.skywalking.apm.plugin.jdbc.define.Constants.COMMIT_METHOD_NAME;
import static org.apache.skywalking.apm.plugin.jdbc.define.Constants.CREATE_STATEMENT_METHOD_NAME;
import static org.apache.skywalking.apm.plugin.jdbc.define.Constants.PREPARE_CALL_METHOD_NAME;
import static org.apache.skywalking.apm.plugin.jdbc.define.Constants.PREPARE_STATEMENT_METHOD_NAME;
import static org.apache.skywalking.apm.plugin.jdbc.define.Constants.RELEASE_SAVE_POINT_METHOD_NAME;
import static org.apache.skywalking.apm.plugin.jdbc.define.Constants.ROLLBACK_METHOD_NAME;
import static org.apache.skywalking.apm.plugin.jdbc.define.Constants.SERVICE_METHOD_INTERCEPT_CLASS;

/**
 * {@link ConnectionInstrumentation} intercepts the following methods that the class which extend {@link
 * com.microsoft.sqlserver.jdbc.SQLServerConnection}. <br/>
 *
 * 1. Enhance <code>prepareStatement</code> by <code>org.skywalking.apm.plugin.jdbc.define.JDBCPrepareStatementInterceptor</code>
 * 2. Enhance <code>prepareCall</code> by <code>org.skywalking.apm.plugin.jdbc.define.JDBCPrepareCallInterceptor</code>
 * 3. Enhance <code>createStatement</code> by <code>org.skywalking.apm.plugin.jdbc.define.JDBCStatementInterceptor</code>
 * 4. Enhance <code>commit, rollback, close, releaseSavepoint</code> by <code>org.skywalking.apm.plugin.jdbc.define.ConnectionServiceMethodInterceptor</code>
 *
 * @author zone1511
 */
public class ConnectionInstrumentation extends ClassInstanceMethodsEnhancePluginDefine {

    @Override protected ConstructorInterceptPoint[] getConstructorsInterceptPoints() {
        return new ConstructorInterceptPoint[0];
    }

    @Override protected InstanceMethodsInterceptPoint[] getInstanceMethodsInterceptPoints() {
        return new InstanceMethodsInterceptPoint[] {
            new InstanceMethodsInterceptPoint() {
                @Override public ElementMatcher<MethodDescription> getMethodsMatcher() {
                    return named(PREPARE_STATEMENT_METHOD_NAME).and(takesArguments(3))
                        .or(named(PREPARE_STATEMENT_METHOD_NAME).and(takesArguments(4)))
                        .or(named(PREPARE_STATEMENT_METHOD_NAME).and(takesArguments(5)));
                }

                @Override public String getMethodsInterceptor() {
                    return "org.apache.skywalking.apm.plugin.jdbc.mssql.CreatePreparedStatementInterceptor";
                }

                @Override public boolean isOverrideArgs() {
                    return false;
                }
            },
            new InstanceMethodsInterceptPoint() {
                @Override public ElementMatcher<MethodDescription> getMethodsMatcher() {
                    return named(PREPARE_CALL_METHOD_NAME);
                }

                @Override public String getMethodsInterceptor() {
                    return "org.apache.skywalking.apm.plugin.jdbc.mssql.CreateCallableStatementInterceptor";
                }

                @Override public boolean isOverrideArgs() {
                    return false;
                }
            },
            new InstanceMethodsInterceptPoint() {
                @Override public ElementMatcher<MethodDescription> getMethodsMatcher() {
                    return
                        named(CREATE_STATEMENT_METHOD_NAME).and(takesArguments(2))
                            .or(named(CREATE_STATEMENT_METHOD_NAME).and(takesArguments(4))
                            );
                }

                @Override public String getMethodsInterceptor() {
                    return "org.apache.skywalking.apm.plugin.jdbc.mssql.CreateStatementInterceptor";
                }

                @Override public boolean isOverrideArgs() {
                    return false;
                }
            },
            new InstanceMethodsInterceptPoint() {
                @Override public ElementMatcher<MethodDescription> getMethodsMatcher() {
                    return named(COMMIT_METHOD_NAME).or(named(ROLLBACK_METHOD_NAME)).or(named(CLOSE_METHOD_NAME)).or(named(RELEASE_SAVE_POINT_METHOD_NAME));
                }

                @Override public String getMethodsInterceptor() {
                    return SERVICE_METHOD_INTERCEPT_CLASS;
                }

                @Override public boolean isOverrideArgs() {
                    return false;
                }
            }
        };

    }

    @Override protected ClassMatch enhanceClass() {
        return MultiClassNameMatch.byMultiClassMatch("com.microsoft.sqlserver.jdbc.SQLServerConnection", "com.microsoft.sqlserver.jdbc.SQLServerConnectionPoolProxy");
    }
}
