package com.mpr.order.business.domain;

import com.mpr.order.business.domain.entity.Order;
import com.mpr.order.business.domain.entity.Restaurant;
import com.mpr.order.business.domain.event.OrderCancelledEvent;
import com.mpr.order.business.domain.event.OrderCreatedEvent;
import com.mpr.order.business.domain.event.OrderPaidEvent;

import java.util.List;

public interface OrderDomainService {

    OrderCreatedEvent validateAndInitiateOrder(Order order, Restaurant restaurant);

    OrderPaidEvent payOrder(Order order);

    void approveOrder(Order order);

    OrderCancelledEvent cancelOrderPayment(Order order, List<String> failureMessages);

    void cancelOrder(Order order, List<String> failureMessages);
}