package com.mpr.order.business.message;

import com.mpr.order.business.dto.message.PaymentResponse;
import com.mpr.order.business.ports.input.message.listener.PaymentResponseMessageListener;
import com.mpr.order.business.saga.OrderPaymentSaga;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import static com.mpr.order.business.domain.entity.Order.FAILURE_MESSAGE_DELIMITER;

@Slf4j
@Validated
@Service
public class PaymentResponseMessageListenerImpl implements PaymentResponseMessageListener {

    private final OrderPaymentSaga orderPaymentSaga;

    public PaymentResponseMessageListenerImpl(OrderPaymentSaga orderPaymentSaga) {
        this.orderPaymentSaga = orderPaymentSaga;
    }

    @Override
    public void paymentCompleted(PaymentResponse paymentResponse) {
        orderPaymentSaga.process(paymentResponse);
        log.info("Order Payment Saga process operation is completed for order id: {}", paymentResponse.getOrderId());
    }

    @Override
    public void paymentCancelled(PaymentResponse paymentResponse) {
        orderPaymentSaga.rollback(paymentResponse);
        log.info("Order is roll backed for order id: {} with failure messages: {}",
                paymentResponse.getOrderId(),
                String.join(FAILURE_MESSAGE_DELIMITER, paymentResponse.getFailureMessages()));
    }
}