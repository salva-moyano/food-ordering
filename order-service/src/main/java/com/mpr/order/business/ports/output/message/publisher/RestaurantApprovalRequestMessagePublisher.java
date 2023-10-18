package com.mpr.order.business.ports.output.message.publisher;

import com.mpr.order.business.outbox.model.OutboxStatus;
import com.mpr.order.business.outbox.model.approval.OrderApprovalOutboxMessage;

import java.util.function.BiConsumer;

public interface RestaurantApprovalRequestMessagePublisher {

    void publish(OrderApprovalOutboxMessage orderApprovalOutboxMessage,
                 BiConsumer<OrderApprovalOutboxMessage, OutboxStatus> outboxCallback);
}