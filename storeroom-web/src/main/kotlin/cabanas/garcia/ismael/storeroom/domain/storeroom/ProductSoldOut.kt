package cabanas.garcia.ismael.storeroom.domain.storeroom

import cabanas.garcia.ismael.storeroom.domain.shared.DomainEvent
import java.time.Instant

data class ProductSoldOut(
        val productId: String,
        val userId: String): DomainEvent {
    override fun id(): String {
        return productId
    }

    override fun ocurredOn(): Instant {
        return Instant.now()
    }
}
