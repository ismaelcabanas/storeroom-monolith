package cabanas.garcia.ismael.storeroom.domain.shared.eventbus

import cabanas.garcia.ismael.storeroom.domain.shared.DomainEvent

class InMemoryEventBus(val eventsPublished: MutableList<DomainEvent> = mutableListOf())
        : EventBus {

    override fun publish(event: DomainEvent) {
        eventsPublished.add(event)
    }

}