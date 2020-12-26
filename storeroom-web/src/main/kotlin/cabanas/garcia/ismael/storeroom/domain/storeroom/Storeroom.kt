package cabanas.garcia.ismael.storeroom.domain.storeroom

import cabanas.garcia.ismael.storeroom.domain.shared.DomainEvent
import cabanas.garcia.ismael.storeroom.domain.storeroom.event.ProductAdded
import cabanas.garcia.ismael.storeroom.domain.storeroom.event.ProductConsumed
import cabanas.garcia.ismael.storeroom.domain.storeroom.event.ProductSoldOut
import cabanas.garcia.ismael.storeroom.domain.storeroom.exception.ProductDoesNotExitsException

class Storeroom(
        val id: StoreroomId,
        val ownerId: UserId,
        val name: String,
        private var products: Set<Product> = setOf(),
        private var events: List<DomainEvent> = listOf()) {

    companion object {
        const val ZERO_STOCK: Int = 0
    }

    fun products(): Set<Product> = products

    fun events(): List<DomainEvent> = events.toList()

    fun addProduct(productId: String, ownerId: String): Storeroom = addProduct(productId, ownerId, ZERO_STOCK)

    fun addProduct(productId: String, ownerId: String, quantity: Int): Storeroom {
        val product = productOf(ProductId(productId))

        products = if (product == null) {
            updateProducts(Product(ProductId(productId), Stock(quantity)))
        } else {
            updateProducts(product.addStock(quantity))
        }

        events = events.plus(ProductAdded(productId, id.value, ownerId, quantity))

        return this
    }

    fun stockOf(productId: String): Int {
        val product = productOf(ProductId(productId))

        return product?.stock() ?: ZERO_STOCK
    }

    fun consumeProduct(productId: String, ownerId: String, quantity: Int): Storeroom {
        val product = productOf(ProductId(productId)) ?: throw ProductDoesNotExitsException(productId)

        val productConsumed = product.consumeStock(quantity)

        products = updateProducts(productConsumed)

        events = events.plus(ProductConsumed(productId, id.value, ownerId, quantity))

        if (productConsumed.stock() == ZERO_STOCK) {
            events = events.plus(ProductSoldOut(productId, id.value, ownerId))
        }

        return this
    }

    private fun updateProducts(product: Product): Set<Product> {
        val currentProducts = products.toMutableSet()
        currentProducts.removeIf { item -> item.id == product.id }
        return currentProducts.plus(product)
    }

    private fun productOf(productId: ProductId): Product? = products.find { product -> product.id == productId }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Storeroom

        if (id != other.id) return false
        if (ownerId != other.ownerId) return false
        if (name != other.name) return false
        if (products != other.products) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + ownerId.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + products.hashCode()
        return result
    }

    override fun toString(): String = "Storeroom(id=$id, ownerId=$ownerId, name='$name', products=$products)"


}
