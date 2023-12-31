package com.mpr.order.infrastructure.persistence.restaurant;

import com.mpr.order.business.domain.entity.Product;
import com.mpr.order.business.domain.entity.Restaurant;
import com.mpr.order.business.domain.valueobject.Money;
import com.mpr.order.business.domain.valueobject.ProductId;
import com.mpr.order.business.domain.valueobject.RestaurantId;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class RestaurantDataAccessMapper {

    public List<UUID> restaurantToRestaurantProducts(Restaurant restaurant) {
        return restaurant.getProducts().stream()
                .map(product -> product.getId().getValue())
                .collect(Collectors.toList());
    }

    public Restaurant restaurantEntityToRestaurant(List<RestaurantEntity> restaurantEntities) {
        RestaurantEntity restaurantEntity =
                restaurantEntities.stream().findFirst().orElseThrow(() ->
                        new RestaurantDataAccessException("Restaurant could not be found!"));

        List<Product> restaurantProducts = restaurantEntities.stream().map(entity ->
                new Product(new ProductId(entity.getProductId()), entity.getProductName(),
                            new Money(entity.getProductPrice()))).toList();

        return Restaurant.builder()
                .restaurantId(new RestaurantId(restaurantEntity.getId()))
                .products(restaurantProducts)
                .active(restaurantEntity.getActive())
                .build();
    }
}
