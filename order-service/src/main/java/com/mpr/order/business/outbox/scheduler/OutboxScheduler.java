package com.mpr.order.business.outbox.scheduler;

public interface OutboxScheduler {
    void processOutboxMessage();
}