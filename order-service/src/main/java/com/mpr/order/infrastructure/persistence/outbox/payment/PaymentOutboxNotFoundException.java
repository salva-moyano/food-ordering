package com.mpr.order.infrastructure.persistence.outbox.payment;

public class PaymentOutboxNotFoundException extends RuntimeException {

    public PaymentOutboxNotFoundException(String message) {
        super(message);
    }
}
