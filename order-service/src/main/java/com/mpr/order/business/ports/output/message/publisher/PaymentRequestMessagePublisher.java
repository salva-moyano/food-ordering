package com.mpr.order.business.ports.output.message.publisher;

import com.mpr.order.business.outbox.model.OutboxStatus;
import com.mpr.order.business.outbox.model.payment.OrderPaymentOutboxMessage;

import java.util.function.BiConsumer;

public interface PaymentRequestMessagePublisher {

    void publish(OrderPaymentOutboxMessage orderPaymentOutboxMessage,
                 BiConsumer<OrderPaymentOutboxMessage, OutboxStatus> outboxCallback);
}
