package cabanas.garcia.ismael.storeroom.infrastructure.shared.bus.spring.event

import cabanas.garcia.ismael.storeroom.domain.storeroom.event.ProductSoldOut
import org.springframework.context.ApplicationEvent

class ProductSoldOutSpringEvent(private val event: ProductSoldOut): ApplicationEvent(event) {
    val productId: String = event.productId
    val storeroomId: String = event.storeroomId
    val userId: String = event.userId

}