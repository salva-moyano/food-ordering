package com.mpr.order.infrastructure.persistence.order;

import com.mpr.order.business.domain.valueobject.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
@Table(name = "orders")
public class OrderEntity {
    @Id
    private final UUID id;
    private final UUID customerId;
    private final UUID restaurantId;
    private final UUID trackingId;
    private final UUID addressId;
    private final BigDecimal price;
    private final OrderStatus orderStatus;
    private final String failureMessages;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderEntity that = (OrderEntity) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
