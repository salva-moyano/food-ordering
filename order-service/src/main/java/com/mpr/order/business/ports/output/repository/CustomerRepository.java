package com.mpr.order.business.ports.output.repository;

import com.mpr.order.business.domain.entity.Customer;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository {

    Optional<Customer> findCustomer(UUID customerId);

    Customer save(Customer customer);
}
