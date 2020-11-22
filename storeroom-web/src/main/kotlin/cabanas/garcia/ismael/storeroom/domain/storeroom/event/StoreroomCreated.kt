package cabanas.garcia.ismael.storeroom.domain.storeroom.event

import cabanas.garcia.ismael.storeroom.domain.shared.DomainEvent
import java.time.Instant

data class StoreroomCreated(
        val storeroomId: String,
        val storeroomOwnerId: String,
        val storeroomName:String): DomainEvent {

    override fun id(): String {
        return storeroomId
    }

    override fun ocurredOn(): Instant {
        return Instant.now()
    }
}