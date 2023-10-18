package com.mpr.order.infrastructure.persistence.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

@RequiredArgsConstructor
@Configuration
public class JdbcConverterConfig {

    private final ObjectMapper objectMapper;

    @ReadingConverter
    private static class IntegerToBooleanConverter implements Converter<Integer, Boolean> {
        @Override
        public Boolean convert(@NonNull Integer source) {
            return source != 0;
        }
    }

    @ReadingConverter
    private static class ByteBooleanConverter implements Converter<Byte, Boolean> {
        @Override
        public Boolean convert(@NonNull Byte source) {
            return source != 0;
        }
    }

    @Bean
    IntegerToBooleanConverter integerToBooleanConverter() {
        return new IntegerToBooleanConverter();
    }

    @Bean
    ByteBooleanConverter byteBooleanConverter() {
        return new ByteBooleanConverter();
    }

}
