package com.mpr.order.infrastructure.persistence.restaurant;

public class RestaurantDataAccessException extends RuntimeException{

    public RestaurantDataAccessException(String message) {
        super(message);
    }
}
