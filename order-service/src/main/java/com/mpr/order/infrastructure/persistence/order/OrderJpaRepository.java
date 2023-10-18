package com.mpr.order.infrastructure.persistence.order;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderJpaRepository extends CrudRepository<OrderEntity, UUID> {

    Optional<OrderEntity> findByTrackingId(UUID trackingId);
}
