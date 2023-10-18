package com.mpr.order.business.ports.input.service;

import com.mpr.order.business.dto.create.CreateOrderCommand;
import com.mpr.order.business.dto.create.CreateOrderResponse;
import com.mpr.order.business.dto.track.TrackOrderQuery;
import com.mpr.order.business.dto.track.TrackOrderResponse;
import jakarta.validation.Valid;

public interface OrderApplicationService {

    CreateOrderResponse createOrder(@Valid CreateOrderCommand createOrderCommand);

    TrackOrderResponse trackOrder(@Valid TrackOrderQuery trackOrderQuery);
}
