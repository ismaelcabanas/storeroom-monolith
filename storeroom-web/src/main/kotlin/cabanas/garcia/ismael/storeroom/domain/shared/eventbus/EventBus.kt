package cabanas.garcia.ismael.storeroom.domain.shared.eventbus

import cabanas.garcia.ismael.storeroom.domain.shared.DomainEvent

interface EventBus {
    fun publish(event: DomainEvent)
}
