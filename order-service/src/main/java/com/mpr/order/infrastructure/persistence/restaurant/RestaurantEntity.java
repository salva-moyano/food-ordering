package com.mpr.order.infrastructure.persistence.restaurant;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;


import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
@Table(name = "restaurant")
public class RestaurantEntity {

    @Id
    private final UUID id;
    private final UUID productId;
    private final String name;
    private final Boolean active;
    private final String productName;
    private final BigDecimal productPrice;
    private final Boolean productAvailable;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RestaurantEntity that = (RestaurantEntity) o;
        return id.equals(that.id) && productId.equals(that.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productId);
    }
}
