package com.mpr.order.infrastructure.persistence.restaurant;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RestaurantJpaRepository extends CrudRepository<RestaurantEntity, UUID> {

    Optional<List<RestaurantEntity>> findByIdAndProductIdIn(UUID restaurantId, List<UUID> productIds);
}
