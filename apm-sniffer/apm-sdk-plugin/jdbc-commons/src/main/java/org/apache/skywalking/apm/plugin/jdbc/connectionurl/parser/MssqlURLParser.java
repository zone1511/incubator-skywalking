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

package org.apache.skywalking.apm.plugin.jdbc.connectionurl.parser;

import org.apache.skywalking.apm.network.trace.component.ComponentsDefine;
import org.apache.skywalking.apm.plugin.jdbc.trace.ConnectionInfo;

public class MssqlURLParser extends AbstractURLParser {
    private static final int DEFAULT_PORT = 1433;
    private static final String DB_TYPE = "Mssql";
    private static final String PROTOCOL_PREFIX = "jdbc:sqlserver://";
    private static final String DEFAULT_HOST = "127.0.0.1";

    public MssqlURLParser() {
        this("");
    }

    public MssqlURLParser(String url) {
        super(url);
    }

    @Override protected URLLocation fetchDatabaseHostsIndexRange() {
        return null;
    }

    @Override protected URLLocation fetchDatabaseNameIndexRange() {
        return null;
    }

    @Override
    public ConnectionInfo parse() {
        //jdbc:sqlserver://[serverName[\instanceName][:portNumber]][;property=value[;property=value]]
        String host = url.substring(PROTOCOL_PREFIX.length());

        String[] parts = host.split(";");
        String hostStr = parts[0];
        int firstProperties = hostStr.indexOf(';');
        int portIndicator = hostStr.indexOf(':');
        int instanceIndicator = hostStr.indexOf('\\');

        String hostname = DEFAULT_HOST;
        int port = DEFAULT_PORT;
        String databaseName = "";
        if (portIndicator >= 0) {
            try {
                port = Integer.parseInt(hostStr.substring(portIndicator + 1));
            } catch (NumberFormatException ex) {
                //not parsable Port number
            }
            hostStr = hostStr.substring(0, portIndicator);
        }
        if (instanceIndicator >= 0) {
            databaseName = hostStr.substring(instanceIndicator + 1);
            hostStr = hostStr.substring(0, instanceIndicator);
        }
        if (hostStr.length() > 0) {
            hostname = hostStr;
        }
        //check database name attribute databaseName, database
        for (int i = 1; i < parts.length; i++) {
            if (parts[i].toUpperCase().startsWith("DATABASENAME=")) {
                databaseName = parts[i].substring("DATABASENAME=".length());
            } else if (parts[i].toUpperCase().startsWith("DATABASE=")) {
                databaseName = parts[i].substring("DATABASE=".length());
            }
        }
        return new ConnectionInfo(ComponentsDefine.MSSQL, DB_TYPE, hostname, port, databaseName);
    }

    public String getJDBCURLPrefix() {
        return PROTOCOL_PREFIX;
    }
}
