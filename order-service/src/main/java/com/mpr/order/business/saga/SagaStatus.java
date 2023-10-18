package com.mpr.order.business.saga;

public enum SagaStatus {
    STARTED, FAILED, SUCCEEDED, PROCESSING, COMPENSATING, COMPENSATED
}
