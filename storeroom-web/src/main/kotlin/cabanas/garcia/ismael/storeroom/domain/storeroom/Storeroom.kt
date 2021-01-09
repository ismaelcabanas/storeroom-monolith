package cabanas.garcia.ismael.storeroom.domain.storeroom

import cabanas.garcia.ismael.storeroom.domain.shared.DomainEvent
import cabanas.garcia.ismael.storeroom.domain.storeroom.event.ProductAdded
import cabanas.garcia.ismael.storeroom.domain.storeroom.event.ProductConsumed
import cabanas.garcia.ismael.storeroom.domain.storeroom.event.ProductSoldOut
import cabanas.garcia.ismael.storeroom.domain.storeroom.exception.ProductDoesNotExitsException

class Storeroom(
        id: String,
        ownerId: String,
        val name: String,
        val products: List<Product> = emptyList()) {

    val id = StoreroomId(id)
    val ownerId = UserId(ownerId)
    private var events = emptyList<DomainEvent>()

    companion object {
        const val ZERO_STOCK: Int = 0
    }

    fun events(): List<DomainEvent> = events

    fun addProduct(productId: String, ownerId: String, quantity: Int = ZERO_STOCK): Storeroom {
        val product = productOf(ProductId(productId))

        val newProducts = if (product == null) {
            updateProducts(Product(productId, quantity))
        } else {
            updateProducts(product.addStock(quantity))
        }

        val newEvents = events.plus(ProductAdded(productId, id.value, ownerId, quantity))

        return Storeroom(this.id.value, this.ownerId.value, this.name, newProducts)
                .apply { events = newEvents }
    }

    fun stockOf(productId: String): Int {
        val product = productOf(ProductId(productId))

        return product?.stock() ?: ZERO_STOCK
    }

    fun consumeProduct(productId: String, ownerId: String, quantity: Int): Storeroom {
        val product = productOf(ProductId(productId)) ?: throw ProductDoesNotExitsException(productId)

        val productConsumed = product.consumeStock(quantity)

        val newProducts = updateProducts(productConsumed)

        var newEvents = events.plus(ProductConsumed(productId, id.value, ownerId, quantity))

        if (productConsumed.stock() == ZERO_STOCK) {
            newEvents = newEvents.plus(ProductSoldOut(productId, id.value, ownerId))
        }

        return Storeroom(this.id.value, this.ownerId.value, this.name, newProducts)
                .apply { events = newEvents }
    }

    private fun updateProducts(product: Product): List<Product> {
        val currentProducts = products.toMutableList()
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
