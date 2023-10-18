package com.mpr.order.infrastructure.persistence.outbox.restaurantapproval;

public class ApprovalOutboxNotFoundException extends RuntimeException {

    public ApprovalOutboxNotFoundException(String message) {
        super(message);
    }
}
