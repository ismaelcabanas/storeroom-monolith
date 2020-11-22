package cabanas.garcia.ismael.storeroom.domain.storeroom

import cabanas.garcia.ismael.storeroom.domain.shared.DomainEvent
import java.time.Instant

data class ProductConsumed(
        val productId: String,
        val storeroomId: String,
        val userId: String,
        val quantity: Int): DomainEvent {
    override fun id(): String {
        return productId
    }

    override fun ocurredOn(): Instant {
        return Instant.now()
    }
}
