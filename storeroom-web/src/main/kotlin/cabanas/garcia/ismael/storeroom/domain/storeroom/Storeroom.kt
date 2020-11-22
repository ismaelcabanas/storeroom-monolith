package cabanas.garcia.ismael.storeroom.domain.storeroom

import cabanas.garcia.ismael.storeroom.domain.shared.DomainEvent

private const val ZERO_STOCK: Int = 0

class Storeroom(
        val id: StoreroomId,
        val ownerId: UserId,
        val name: String,
        private var products: Set<Product> = setOf(),
        private var events: List<DomainEvent> = listOf()) {

    companion object {
        fun create(storeroomId: String, ownerId: String, storeroomName: String): Storeroom {
            return Storeroom(StoreroomId(storeroomId), UserId(ownerId), storeroomName)
        }
    }

    fun products(): Set<Product> = products

    fun addProduct(productId: String, ownerId: String): Storeroom {
        return addProduct(productId, ownerId, ZERO_STOCK)
    }

    fun addProduct(productId: String, ownerId: String, quantity: Int): Storeroom {
        val product = productOf(ProductId(productId))

        products = if (product == null) {
            updateProducts(Product(ProductId(productId), Stock(quantity)))
        } else {
            updateProducts(product.addStock(quantity))
        }

        return this
    }

    fun stockOf(productId: String): Int {
        val product = productOf(ProductId(productId))

        return product?.stock() ?: ZERO_STOCK
    }

    fun consumeProduct(productId: String, ownerId: String, quantity: Int): Storeroom {
        val product = productOf(ProductId(productId)) ?: throw ProductDoesNotExitsException(productId)

        products = updateProducts(product.consumeStock(quantity))

        return this
    }

    private fun updateProducts(product: Product): Set<Product> {
        val currentProducts = products.toMutableSet()
        currentProducts.removeIf { item -> item.id == product.id }
        return currentProducts.plus(product)
    }

    private fun productOf(productId: ProductId): Product? {
        return products.find { product -> product.id == productId }
    }

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

    override fun toString(): String {
        return "Storeroom(id=$id, ownerId=$ownerId, name='$name', products=$products)"
    }

}
