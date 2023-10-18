package com.mpr.order.business;

import com.mpr.order.business.dto.track.TrackOrderQuery;
import com.mpr.order.business.dto.track.TrackOrderResponse;
import com.mpr.order.business.mapper.OrderDataMapper;
import com.mpr.order.business.ports.output.repository.OrderRepository;
import com.mpr.order.business.domain.entity.Order;
import com.mpr.order.business.domain.exception.OrderNotFoundException;
import com.mpr.order.business.domain.valueobject.TrackingId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderTrackCommandHandler {

    private final OrderDataMapper orderDataMapper;

    private final OrderRepository orderRepository;


    @Transactional(readOnly = true)
    public TrackOrderResponse trackOrder(TrackOrderQuery trackOrderQuery) {
           Optional<Order> orderResult =
                   orderRepository.findByTrackingId(new TrackingId(trackOrderQuery.getOrderTrackingId()));
           if (orderResult.isEmpty()) {
               log.warn("Could not find order with tracking id: {}", trackOrderQuery.getOrderTrackingId());
               throw new OrderNotFoundException("Could not find order with tracking id: " +
                                                trackOrderQuery.getOrderTrackingId());
           }
           return orderDataMapper.orderToTrackOrderResponse(orderResult.get());
    }
}
