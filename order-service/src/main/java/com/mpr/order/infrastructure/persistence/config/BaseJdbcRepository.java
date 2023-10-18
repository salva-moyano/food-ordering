package com.mpr.order.infrastructure.persistence.config;

import org.springframework.data.jdbc.core.JdbcAggregateOperations;
import org.springframework.data.jdbc.core.convert.JdbcConverter;
import org.springframework.data.jdbc.repository.support.SimpleJdbcRepository;
import org.springframework.data.mapping.PersistentEntity;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

public class BaseJdbcRepository<T, ID extends Serializable> extends SimpleJdbcRepository<T, ID> {

    private final JdbcAggregateOperations entityOperations;

    public BaseJdbcRepository(JdbcAggregateOperations entityOperations, PersistentEntity<T, ?> entity, JdbcConverter jdbcConverter) {
        super(entityOperations, entity, jdbcConverter);
        this.entityOperations = entityOperations;
    }

    @Transactional
    public <S extends T> S create(S entity) {
        return entityOperations.insert(entity);
    }

    @Transactional
    public <S extends T> S update(S entity) {
        return entityOperations.update(entity);
    }
}
