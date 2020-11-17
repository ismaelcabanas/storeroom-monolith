package cabanas.garcia.ismael.storeroom.domain.storeroom

private const val ZERO_STOCK: Int = 0

class Storeroom internal constructor(
        val id: StoreroomId,
        val ownerId: UserId,
        val name: String,
        val products: List<Product> = listOf()) {

    companion object {
        fun create(storeroomId: String, ownerId: String, storeroomName: String): Storeroom {
            return Storeroom(StoreroomId(storeroomId), UserId(ownerId), storeroomName)
        }
    }

    fun addProduct(productId: String, ownerId: String): Storeroom {
        return addProduct(productId, ownerId, ZERO_STOCK)
    }

    fun addProduct(productId: String, ownerId: String, quantity: Int): Storeroom {
        val product = productOf(ProductId(productId))

        return if (product == null) {
            Storeroom(id, this.ownerId, name, updateProducts(Product(ProductId(productId), Stock(quantity))))
        } else {
            Storeroom(id, this.ownerId, name, updateProducts(product.addStock(quantity)))
        }
    }

    fun stockOf(productId: String): Int {
        val product = productOf(ProductId(productId))

        return product?.stock() ?: ZERO_STOCK
    }

    fun consumeProduct(productId: String, ownerId: String, quantity: Int): Storeroom {
        val product = productOf(ProductId(productId)) ?: throw ProductDoesNotExitsException(productId)

        return Storeroom(id, this.ownerId, name, updateProducts(product.consumeStock(quantity)))
    }

    private fun updateProducts(product: Product): List<Product> {
        val currentProducts = products.toMutableList()
        currentProducts.removeIf { item -> item.id == product.id }
        currentProducts.add(product)

        return currentProducts
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
