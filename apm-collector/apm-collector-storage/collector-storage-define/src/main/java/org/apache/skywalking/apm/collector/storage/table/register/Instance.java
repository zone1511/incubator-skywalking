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

package org.apache.skywalking.apm.collector.storage.table.register;

import org.apache.skywalking.apm.collector.core.data.Column;
import org.apache.skywalking.apm.collector.core.data.RemoteData;
import org.apache.skywalking.apm.collector.core.data.StreamData;
import org.apache.skywalking.apm.collector.core.data.operator.CoverMergeOperation;
import org.apache.skywalking.apm.collector.core.data.operator.NonMergeOperation;
import org.apache.skywalking.apm.collector.remote.service.RemoteDataRegisterService;

/**
 * @author peng-yongsheng
 */
public class Instance extends StreamData {

    private static final Column[] STRING_COLUMNS = {
        new Column(InstanceTable.COLUMN_ID, new NonMergeOperation()),
        new Column(InstanceTable.COLUMN_AGENT_UUID, new CoverMergeOperation()),
        new Column(InstanceTable.COLUMN_OS_INFO, new CoverMergeOperation()),
        new Column(InstanceTable.COLUMN_APPLICATION_CODE, new CoverMergeOperation()),
    };

    private static final Column[] LONG_COLUMNS = {
        new Column(InstanceTable.COLUMN_REGISTER_TIME, new CoverMergeOperation()),
        new Column(InstanceTable.COLUMN_HEARTBEAT_TIME, new CoverMergeOperation()),
    };

    private static final Column[] DOUBLE_COLUMNS = {};

    private static final Column[] INTEGER_COLUMNS = {
        new Column(InstanceTable.COLUMN_APPLICATION_ID, new CoverMergeOperation()),
        new Column(InstanceTable.COLUMN_INSTANCE_ID, new CoverMergeOperation()),
        new Column(InstanceTable.COLUMN_ADDRESS_ID, new CoverMergeOperation()),
        new Column(InstanceTable.COLUMN_IS_ADDRESS, new CoverMergeOperation()),
    };

    private static final Column[] BYTE_COLUMNS = {};

    public Instance() {
        super(STRING_COLUMNS, LONG_COLUMNS, DOUBLE_COLUMNS, INTEGER_COLUMNS, BYTE_COLUMNS);
    }

    @Override public String getId() {
        return getDataString(0);
    }

    @Override public void setId(String id) {
        setDataString(0, id);
    }

    @Override public String getMetricId() {
        return getId();
    }

    @Override public void setMetricId(String metricId) {
        setId(metricId);
    }

    public int getApplicationId() {
        return getDataInteger(0);
    }

    public void setApplicationId(Integer applicationId) {
        setDataInteger(0, applicationId);
    }

    public String getAgentUUID() {
        return getDataString(1);
    }

    public void setAgentUUID(String agentUUID) {
        setDataString(1, agentUUID);
    }

    public long getRegisterTime() {
        return getDataLong(0);
    }

    public void setRegisterTime(Long registerTime) {
        setDataLong(0, registerTime);
    }

    public int getInstanceId() {
        return getDataInteger(1);
    }

    public void setInstanceId(Integer instanceId) {
        setDataInteger(1, instanceId);
    }

    public long getHeartBeatTime() {
        return getDataLong(1);
    }

    public void setHeartBeatTime(Long heartBeatTime) {
        setDataLong(1, heartBeatTime);
    }

    public String getOsInfo() {
        return getDataString(2);
    }

    public void setOsInfo(String osInfo) {
        setDataString(2, osInfo);
    }

    public String getApplicationCode() {
        return getDataString(3);
    }

    public void setApplicationCode(String applicationCode) {
        setDataString(3, applicationCode);
    }

    public int getAddressId() {
        return getDataInteger(2);
    }

    public void setAddressId(int addressId) {
        setDataInteger(2, addressId);
    }

    public int getIsAddress() {
        return getDataInteger(3);
    }

    public void setIsAddress(int isAddress) {
        setDataInteger(3, isAddress);
    }

    public static class InstanceCreator implements RemoteDataRegisterService.RemoteDataInstanceCreator {
        @Override public RemoteData createInstance() {
            return new Instance();
        }
    }
}
