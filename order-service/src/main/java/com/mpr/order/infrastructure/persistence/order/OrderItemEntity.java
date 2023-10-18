package com.mpr.order.infrastructure.persistence.order;

import lombok.*;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
@Table(name = "order_items")
public class OrderItemEntity {
    @Id
    private final Long id;
    private final UUID orderId;
    private final UUID productId;
    private final BigDecimal price;
    private final Integer quantity;
    private final BigDecimal subTotal;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItemEntity that = (OrderItemEntity) o;
        return id.equals(that.id) && orderId.equals(that.orderId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderId);
    }
}
