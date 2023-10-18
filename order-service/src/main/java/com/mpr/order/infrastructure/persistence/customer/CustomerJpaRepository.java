package com.mpr.order.infrastructure.persistence.customer;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CustomerJpaRepository extends CrudRepository<CustomerEntity, UUID> {

    CustomerEntity create(CustomerEntity customerEntity);

    CustomerEntity update(CustomerEntity customerEntity);
}
