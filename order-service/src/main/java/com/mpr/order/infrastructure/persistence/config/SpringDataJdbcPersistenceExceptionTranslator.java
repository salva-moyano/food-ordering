package com.mpr.order.infrastructure.persistence.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.support.PersistenceExceptionTranslator;
import org.springframework.data.relational.core.conversion.DbActionExecutionException;
import org.springframework.stereotype.Component;

/**
 *  Exception translator create as workaround for http://jira.spring.io/browse/DATAJDBC-611
 */
@Component
@ConditionalOnClass(DbActionExecutionException.class)
public class SpringDataJdbcPersistenceExceptionTranslator implements PersistenceExceptionTranslator {
    @Override
    public DataAccessException translateExceptionIfPossible(RuntimeException ex) {
        if (ex instanceof DbActionExecutionException
                && ex.getCause() instanceof DataAccessException dataAccessException) {
            return dataAccessException;
        }
        return null;
    }
}
