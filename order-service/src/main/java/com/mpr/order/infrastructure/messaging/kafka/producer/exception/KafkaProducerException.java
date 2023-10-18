package com.mpr.order.infrastructure.messaging.kafka.producer.exception;

public class KafkaProducerException extends RuntimeException {

    public KafkaProducerException(String message) {
        super(message);
    }
}
