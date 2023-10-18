package com.mpr.order.infrastructure.persistence.config;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.jdbc.core.convert.JdbcCustomConversions;
import org.springframework.data.jdbc.core.mapping.JdbcMappingContext;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.data.relational.RelationalManagedTypes;
import org.springframework.data.relational.core.mapping.NamingStrategy;
import org.springframework.jdbc.core.PreparedStatementSetter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.lang.Integer.MIN_VALUE;

@Configuration
@EnableJdbcRepositories(basePackages = "com.mpr.order.infrastructure.persistence", repositoryBaseClass = BaseJdbcRepository.class)
public class PersistenceConfig extends AbstractJdbcConfiguration {

    private final List<Converter<?,?>> converters = new ArrayList<>();

    @Autowired(required = false)
    void setConverters(List<Converter<?,?>> otherConverters) {
        converters.addAll(otherConverters);
    }

    @Override
    @NonNull
    public JdbcCustomConversions jdbcCustomConversions() {
        return new JdbcCustomConversions(converters);
    }

    @Bean
    @Override
    public JdbcMappingContext jdbcMappingContext(Optional<NamingStrategy> namingStrategy,
                                                 JdbcCustomConversions customConversions,
                                                 RelationalManagedTypes jdbcManagedTypes) {
        JdbcMappingContext mappingContext = super.jdbcMappingContext(namingStrategy, customConversions, jdbcManagedTypes);
        mappingContext.setForceQuote(false);
        return mappingContext;
    }

    @Bean
    PreparedStatementSetter streamingPreparedStatement() {
        return ps -> ps.setFetchSize(MIN_VALUE);
    }
}
