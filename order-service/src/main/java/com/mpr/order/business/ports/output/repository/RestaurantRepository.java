package com.mpr.order.business.ports.output.repository;

import com.mpr.order.business.domain.entity.Restaurant;

import java.util.Optional;

public interface RestaurantRepository {

    Optional<Restaurant> findRestaurantInformation(Restaurant restaurant);
}