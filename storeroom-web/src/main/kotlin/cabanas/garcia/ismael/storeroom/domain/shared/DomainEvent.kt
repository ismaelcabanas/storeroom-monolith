package cabanas.garcia.ismael.storeroom.domain.shared

import java.time.Instant

interface DomainEvent {
    fun id(): String

    fun ocurredOn(): Instant
}