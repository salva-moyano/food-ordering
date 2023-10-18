package com.mpr.order.business.domain.event;

public interface DomainEventPublisher<T extends DomainEvent> {

    void publish(T domainEvent);
}
