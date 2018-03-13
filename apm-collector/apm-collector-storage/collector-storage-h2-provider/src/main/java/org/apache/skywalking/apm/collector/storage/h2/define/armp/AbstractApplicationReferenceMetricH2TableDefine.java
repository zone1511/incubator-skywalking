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

package org.apache.skywalking.apm.collector.storage.h2.define.armp;

import org.apache.skywalking.apm.collector.storage.h2.base.define.H2ColumnDefine;
import org.apache.skywalking.apm.collector.storage.h2.base.define.H2TableDefine;
import org.apache.skywalking.apm.collector.storage.table.application.ApplicationReferenceMetricTable;

/**
 * @author peng-yongsheng
 */
public abstract class AbstractApplicationReferenceMetricH2TableDefine extends H2TableDefine {

    AbstractApplicationReferenceMetricH2TableDefine(String name) {
        super(name);
    }

    @Override public final void initialize() {
        addColumn(new H2ColumnDefine(ApplicationReferenceMetricTable.COLUMN_ID, H2ColumnDefine.Type.Varchar.name()));
        addColumn(new H2ColumnDefine(ApplicationReferenceMetricTable.COLUMN_METRIC_ID, H2ColumnDefine.Type.Varchar.name()));
        addColumn(new H2ColumnDefine(ApplicationReferenceMetricTable.COLUMN_FRONT_APPLICATION_ID, H2ColumnDefine.Type.Int.name()));
        addColumn(new H2ColumnDefine(ApplicationReferenceMetricTable.COLUMN_BEHIND_APPLICATION_ID, H2ColumnDefine.Type.Int.name()));
        addColumn(new H2ColumnDefine(ApplicationReferenceMetricTable.COLUMN_SOURCE_VALUE, H2ColumnDefine.Type.Int.name()));

        addColumn(new H2ColumnDefine(ApplicationReferenceMetricTable.COLUMN_TRANSACTION_CALLS, H2ColumnDefine.Type.Bigint.name()));
        addColumn(new H2ColumnDefine(ApplicationReferenceMetricTable.COLUMN_TRANSACTION_ERROR_CALLS, H2ColumnDefine.Type.Bigint.name()));
        addColumn(new H2ColumnDefine(ApplicationReferenceMetricTable.COLUMN_TRANSACTION_DURATION_SUM, H2ColumnDefine.Type.Bigint.name()));
        addColumn(new H2ColumnDefine(ApplicationReferenceMetricTable.COLUMN_TRANSACTION_ERROR_DURATION_SUM, H2ColumnDefine.Type.Bigint.name()));
        addColumn(new H2ColumnDefine(ApplicationReferenceMetricTable.COLUMN_TRANSACTION_AVERAGE_DURATION, H2ColumnDefine.Type.Bigint.name()));

        addColumn(new H2ColumnDefine(ApplicationReferenceMetricTable.COLUMN_BUSINESS_TRANSACTION_CALLS, H2ColumnDefine.Type.Bigint.name()));
        addColumn(new H2ColumnDefine(ApplicationReferenceMetricTable.COLUMN_BUSINESS_TRANSACTION_ERROR_CALLS, H2ColumnDefine.Type.Bigint.name()));
        addColumn(new H2ColumnDefine(ApplicationReferenceMetricTable.COLUMN_BUSINESS_TRANSACTION_DURATION_SUM, H2ColumnDefine.Type.Bigint.name()));
        addColumn(new H2ColumnDefine(ApplicationReferenceMetricTable.COLUMN_BUSINESS_TRANSACTION_ERROR_DURATION_SUM, H2ColumnDefine.Type.Bigint.name()));
        addColumn(new H2ColumnDefine(ApplicationReferenceMetricTable.COLUMN_BUSINESS_TRANSACTION_AVERAGE_DURATION, H2ColumnDefine.Type.Bigint.name()));

        addColumn(new H2ColumnDefine(ApplicationReferenceMetricTable.COLUMN_MQ_TRANSACTION_CALLS, H2ColumnDefine.Type.Bigint.name()));
        addColumn(new H2ColumnDefine(ApplicationReferenceMetricTable.COLUMN_MQ_TRANSACTION_ERROR_CALLS, H2ColumnDefine.Type.Bigint.name()));
        addColumn(new H2ColumnDefine(ApplicationReferenceMetricTable.COLUMN_MQ_TRANSACTION_DURATION_SUM, H2ColumnDefine.Type.Bigint.name()));
        addColumn(new H2ColumnDefine(ApplicationReferenceMetricTable.COLUMN_MQ_TRANSACTION_ERROR_DURATION_SUM, H2ColumnDefine.Type.Bigint.name()));
        addColumn(new H2ColumnDefine(ApplicationReferenceMetricTable.COLUMN_MQ_TRANSACTION_AVERAGE_DURATION, H2ColumnDefine.Type.Bigint.name()));

        addColumn(new H2ColumnDefine(ApplicationReferenceMetricTable.COLUMN_SATISFIED_COUNT, H2ColumnDefine.Type.Bigint.name()));
        addColumn(new H2ColumnDefine(ApplicationReferenceMetricTable.COLUMN_TOLERATING_COUNT, H2ColumnDefine.Type.Bigint.name()));
        addColumn(new H2ColumnDefine(ApplicationReferenceMetricTable.COLUMN_FRUSTRATED_COUNT, H2ColumnDefine.Type.Bigint.name()));
        addColumn(new H2ColumnDefine(ApplicationReferenceMetricTable.COLUMN_TIME_BUCKET, H2ColumnDefine.Type.Bigint.name()));
    }
}
