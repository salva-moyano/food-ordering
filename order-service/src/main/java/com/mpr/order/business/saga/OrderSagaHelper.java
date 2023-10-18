package com.mpr.order.business.saga;

import com.mpr.order.business.ports.output.repository.OrderRepository;
import com.mpr.order.business.domain.entity.Order;
import com.mpr.order.business.domain.exception.OrderNotFoundException;
import com.mpr.order.business.domain.valueobject.OrderId;
import com.mpr.order.business.domain.valueobject.OrderStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderSagaHelper {

    private final OrderRepository orderRepository;

    public Order findOrder(String orderId) {
        Optional<Order> orderResponse = orderRepository.findById(new OrderId(UUID.fromString(orderId)));
        if (orderResponse.isEmpty()) {
            log.error("Order with id: {} could not be found!", orderId);
            throw new OrderNotFoundException("Order with id " + orderId + " could not be found!");
        }
        return orderResponse.get();
    }

    public void saveOrder(Order order) {
        orderRepository.save(order);
    }

    public SagaStatus orderStatusToSagaStatus(OrderStatus orderStatus) {
        return switch (orderStatus) {
            case PAID -> SagaStatus.PROCESSING;
            case APPROVED -> SagaStatus.SUCCEEDED;
            case CANCELLING -> SagaStatus.COMPENSATING;
            case CANCELLED -> SagaStatus.COMPENSATED;
            default -> SagaStatus.STARTED;
        };
    }
}