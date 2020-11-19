package cabanas.garcia.ismael.storeroom.domain.shared.eventbus

import cabanas.garcia.ismael.storeroom.domain.shared.DomainEvent
import java.util.concurrent.ConcurrentHashMap

class InMemoryEventBus(private val eventsPublished: MutableMap<String, DomainEvent> = ConcurrentHashMap())
        : EventBus {

    override fun publish(event: DomainEvent) {
        eventsPublished[event.id()] = event
    }

    fun get(id: String): DomainEvent? {
        return eventsPublished[id]
    }
}