package com.mpr.order.business.saga;

public interface SagaStep<T> {
    void process(T data);
    void rollback(T data);
}