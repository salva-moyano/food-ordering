package com.mpr.order.infrastructure.persistence.outbox.payment;

import com.mpr.order.business.domain.valueobject.OrderStatus;
import com.mpr.order.business.outbox.model.OutboxStatus;
import com.mpr.order.business.saga.SagaStatus;
import lombok.*;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Table;

import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.UUID;

@Getter
@Builder
@Table(name = "payment_outbox")
public class PaymentOutboxEntity {

    @Id
    private final UUID id;
    private final UUID sagaId;
    private final ZonedDateTime createdAt;
    private final ZonedDateTime processedAt;
    private final String type;
    private final String payload;
    private final SagaStatus sagaStatus;
    private final OrderStatus orderStatus;
    private final OutboxStatus outboxStatus;
    @Version
    private int version;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentOutboxEntity that = (PaymentOutboxEntity) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

