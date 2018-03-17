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

package org.apache.skywalking.apm.plugin.jdbc.mssql;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.Properties;
import org.apache.skywalking.apm.agent.core.plugin.interceptor.enhance.EnhancedInstance;
import org.apache.skywalking.apm.agent.core.plugin.interceptor.enhance.InstanceMethodsAroundInterceptor;
import org.apache.skywalking.apm.agent.core.plugin.interceptor.enhance.MethodInterceptResult;
import org.apache.skywalking.apm.network.trace.component.ComponentsDefine;
import org.apache.skywalking.apm.plugin.jdbc.connectionurl.parser.MssqlURLParser;
import org.apache.skywalking.apm.plugin.jdbc.trace.ConnectionInfo;

/**
 * {@link JDBCDriverInterceptor} set <code>ConnectionInfo</code> to {@link Connection} object when {@link
 * java.sql.Driver} to create connection, instead of the  {@link Connection} instance.
 *
 * @author zone1511
 */
public class JDBCDriverInterceptor implements InstanceMethodsAroundInterceptor {

    @Override public void beforeMethod(EnhancedInstance objInst, Method method, Object[] allArguments,
        Class<?>[] argumentsTypes, MethodInterceptResult result) throws Throwable {

    }

    @Override public Object afterMethod(EnhancedInstance objInst, Method method, Object[] allArguments,
        Class<?>[] argumentsTypes, Object ret) throws Throwable {
        if (ret != null) {
            if (allArguments[0] instanceof String) {
                ((EnhancedInstance)ret).setSkyWalkingDynamicField(new MssqlURLParser((String)allArguments[0]).parse());
            } else {
                Properties prop = (Properties)allArguments[0];
                String hosts = (String)prop.getProperty("serverName");
                String portNumber = prop.getProperty("portNumber", "1433");
                hosts = hosts + ":" + portNumber;
                String databaseName = prop.getProperty("databaseName", "");

                ConnectionInfo conn = new ConnectionInfo(ComponentsDefine.MSSQL, MssqlURLParser.DB_TYPE, hosts, databaseName);
                ((EnhancedInstance)ret).setSkyWalkingDynamicField(conn);
            }
        }

        return ret;
    }

    @Override public void handleMethodException(EnhancedInstance objInst, Method method, Object[] allArguments,
        Class<?>[] argumentsTypes, Throwable t) {

    }
}
