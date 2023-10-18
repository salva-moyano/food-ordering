package com.mpr.order.business.ports.output.repository;

import com.mpr.order.business.domain.entity.Order;
import com.mpr.order.business.domain.valueobject.OrderId;
import com.mpr.order.business.domain.valueobject.TrackingId;

import java.util.Optional;

public interface OrderRepository {

    Order save(Order order);

    Optional<Order> findById(OrderId orderId);

    Optional<Order> findByTrackingId(TrackingId trackingId);
}