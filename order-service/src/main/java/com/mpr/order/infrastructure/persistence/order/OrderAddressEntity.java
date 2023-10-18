package com.mpr.order.infrastructure.persistence.order;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Objects;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
@Table(name = "order_address")
public class OrderAddressEntity {
    @Id
    private final UUID id;
    private final UUID order;
    private final String street;
    private final String postalCode;
    private final String city;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderAddressEntity that = (OrderAddressEntity) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
