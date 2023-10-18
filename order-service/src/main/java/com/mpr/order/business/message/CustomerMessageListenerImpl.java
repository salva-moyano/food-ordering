package com.mpr.order.business.message;

import com.mpr.order.business.dto.message.CustomerModel;
import com.mpr.order.business.mapper.OrderDataMapper;
import com.mpr.order.business.ports.input.message.listener.CustomerMessageListener;
import com.mpr.order.business.ports.output.repository.CustomerRepository;
import com.mpr.order.business.domain.entity.Customer;
import com.mpr.order.business.domain.exception.OrderDomainException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerMessageListenerImpl implements CustomerMessageListener {

    private final CustomerRepository customerRepository;
    private final OrderDataMapper orderDataMapper;

    @Override
    public void customerCreated(CustomerModel customerModel) {
        Customer customer = customerRepository.save(orderDataMapper.customerModelToCustomer(customerModel));
        if (customer == null) {
            log.error("Customer could not be created in order database with id: {}", customerModel.getId());
            throw new OrderDomainException("Customer could not be created in order database with id " +
                                           customerModel.getId());
        }
        log.info("Customer is created in order database with id: {}", customer.getId());
    }
}
