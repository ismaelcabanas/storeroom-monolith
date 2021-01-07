package cabanas.garcia.ismael.storeroom.infrastructure.shared.bus.spring

import cabanas.garcia.ismael.storeroom.domain.shared.DomainEvent
import cabanas.garcia.ismael.storeroom.domain.shared.eventbus.EventBus
import cabanas.garcia.ismael.storeroom.domain.storeroom.event.ProductSoldOut
import cabanas.garcia.ismael.storeroom.infrastructure.shared.bus.spring.event.ProductSoldOutSpringEvent
import org.springframework.context.ApplicationEvent
import org.springframework.context.ApplicationEventPublisher
import java.lang.RuntimeException

class SpringEventBus(private val eventPublisher: ApplicationEventPublisher): EventBus {
    override fun publish(event: DomainEvent) {
        eventPublisher.publishEvent(toSpringEvent(event))
    }

    override fun publish(events: List<DomainEvent>) {
        events.forEach { publish(it) }
    }

    private fun toSpringEvent(event: DomainEvent): ApplicationEvent {
        if (event is ProductSoldOut) {
            return ProductSoldOutSpringEvent(event)
        }

        throw RuntimeException("Cannot produce event from unknown domain event.")
    }
}