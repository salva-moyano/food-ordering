package com.mpr.order.business.message;

import com.mpr.order.business.dto.message.RestaurantApprovalResponse;
import com.mpr.order.business.ports.input.message.listener.RestaurantApprovalResponseMessageListener;
import com.mpr.order.business.saga.OrderApprovalSaga;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import static com.mpr.order.business.domain.entity.Order.FAILURE_MESSAGE_DELIMITER;

@Slf4j
@Validated
@Service
@RequiredArgsConstructor
public class RestaurantApprovalResponseMessageListenerImpl implements RestaurantApprovalResponseMessageListener {

    private final OrderApprovalSaga orderApprovalSaga;

    @Override
    public void orderApproved(RestaurantApprovalResponse restaurantApprovalResponse) {
        orderApprovalSaga.process(restaurantApprovalResponse);
        log.info("Order is approved for order id: {}", restaurantApprovalResponse.getOrderId());
    }

    @Override
    public void orderRejected(RestaurantApprovalResponse restaurantApprovalResponse) {
          orderApprovalSaga.rollback(restaurantApprovalResponse);
          log.info("Order Approval Saga rollback operation is completed for order id: {} with failure messages: {}",
                  restaurantApprovalResponse.getOrderId(),
                  String.join(FAILURE_MESSAGE_DELIMITER, restaurantApprovalResponse.getFailureMessages()));
    }
}
