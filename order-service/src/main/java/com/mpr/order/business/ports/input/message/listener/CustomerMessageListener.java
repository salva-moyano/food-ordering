package com.mpr.order.business.ports.input.message.listener;

import com.mpr.order.business.dto.message.CustomerModel;

public interface CustomerMessageListener {

    void customerCreated(CustomerModel customerModel);
}
