package com.mpr.order.infrastructure.persistence.customer;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Getter
@Builder
@Table(name = "customers")
public class CustomerEntity {

    @Id
    private final UUID id;
    private final String username;
    private final String firstName;
    private final String lastName;
}
