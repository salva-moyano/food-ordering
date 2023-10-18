package com.mpr.order.business.ports.input.message.listener;

import com.mpr.order.business.dto.message.PaymentResponse;

public interface PaymentResponseMessageListener {

    void paymentCompleted(PaymentResponse paymentResponse);

    void paymentCancelled(PaymentResponse paymentResponse);
}