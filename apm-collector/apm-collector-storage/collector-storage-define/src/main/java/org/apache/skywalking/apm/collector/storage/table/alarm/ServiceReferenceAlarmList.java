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

package org.apache.skywalking.apm.collector.storage.table.alarm;

import org.apache.skywalking.apm.collector.core.data.Column;
import org.apache.skywalking.apm.collector.core.data.StreamData;
import org.apache.skywalking.apm.collector.core.data.operator.CoverMergeOperation;
import org.apache.skywalking.apm.collector.core.data.operator.NonMergeOperation;

/**
 * @author peng-yongsheng
 */
public class ServiceReferenceAlarmList extends StreamData {

    private static final Column[] STRING_COLUMNS = {
        new Column(ServiceReferenceAlarmListTable.COLUMN_ID, new NonMergeOperation()),
        new Column(ServiceReferenceAlarmListTable.COLUMN_ALARM_CONTENT, new CoverMergeOperation()),
    };

    private static final Column[] LONG_COLUMNS = {
        new Column(ServiceReferenceAlarmListTable.COLUMN_TIME_BUCKET, new NonMergeOperation()),
    };

    private static final Column[] DOUBLE_COLUMNS = {};

    private static final Column[] INTEGER_COLUMNS = {
        new Column(ServiceReferenceAlarmListTable.COLUMN_ALARM_TYPE, new NonMergeOperation()),
        new Column(ServiceReferenceAlarmListTable.COLUMN_SOURCE_VALUE, new NonMergeOperation()),
        new Column(ServiceReferenceAlarmListTable.COLUMN_FRONT_APPLICATION_ID, new NonMergeOperation()),
        new Column(ServiceReferenceAlarmListTable.COLUMN_BEHIND_APPLICATION_ID, new NonMergeOperation()),
        new Column(ServiceReferenceAlarmListTable.COLUMN_FRONT_INSTANCE_ID, new NonMergeOperation()),
        new Column(ServiceReferenceAlarmListTable.COLUMN_BEHIND_INSTANCE_ID, new NonMergeOperation()),
        new Column(ServiceReferenceAlarmListTable.COLUMN_FRONT_SERVICE_ID, new NonMergeOperation()),
        new Column(ServiceReferenceAlarmListTable.COLUMN_BEHIND_SERVICE_ID, new NonMergeOperation()),
    };

    private static final Column[] BYTE_COLUMNS = {};

    public ServiceReferenceAlarmList() {
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

    public Integer getAlarmType() {
        return getDataInteger(0);
    }

    public void setAlarmType(Integer alarmType) {
        setDataInteger(0, alarmType);
    }

    public Integer getSourceValue() {
        return getDataInteger(1);
    }

    public void setSourceValue(Integer sourceValue) {
        setDataInteger(1, sourceValue);
    }

    public Integer getFrontApplicationId() {
        return getDataInteger(2);
    }

    public void setFrontApplicationId(Integer frontApplicationId) {
        setDataInteger(2, frontApplicationId);
    }

    public Integer getBehindApplicationId() {
        return getDataInteger(3);
    }

    public void setBehindApplicationId(Integer behindApplicationId) {
        setDataInteger(3, behindApplicationId);
    }

    public Integer getFrontInstanceId() {
        return getDataInteger(4);
    }

    public void setFrontInstanceId(Integer frontInstanceId) {
        setDataInteger(4, frontInstanceId);
    }

    public Integer getBehindInstanceId() {
        return getDataInteger(5);
    }

    public void setBehindInstanceId(Integer behindInstanceId) {
        setDataInteger(5, behindInstanceId);
    }

    public Integer getFrontServiceId() {
        return getDataInteger(6);
    }

    public void setFrontServiceId(Integer frontServiceId) {
        setDataInteger(6, frontServiceId);
    }

    public Integer getBehindServiceId() {
        return getDataInteger(7);
    }

    public void setBehindServiceId(Integer behindServiceId) {
        setDataInteger(7, behindServiceId);
    }

    public Long getTimeBucket() {
        return getDataLong(0);
    }

    public void setTimeBucket(Long timeBucket) {
        setDataLong(0, timeBucket);
    }

    public String getAlarmContent() {
        return getDataString(1);
    }

    public void setAlarmContent(String alarmContent) {
        setDataString(1, alarmContent);
    }
}
