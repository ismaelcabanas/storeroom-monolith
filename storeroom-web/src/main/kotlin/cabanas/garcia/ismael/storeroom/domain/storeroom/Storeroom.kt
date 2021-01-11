package cabanas.garcia.ismael.storeroom.domain.storeroom

import cabanas.garcia.ismael.storeroom.domain.shared.DomainEvent
import cabanas.garcia.ismael.storeroom.domain.storeroom.event.ProductAdded
import cabanas.garcia.ismael.storeroom.domain.storeroom.event.ProductConsumed
import cabanas.garcia.ismael.storeroom.domain.storeroom.event.ProductSoldOut
import cabanas.garcia.ismael.storeroom.domain.storeroom.exception.ProductDoesNotExitsException

class Storeroom(
        id: String,
        ownerId: String,
        val name: String) {

    constructor(id: String,
                ownerId: String,
                name: String,
                products: List<Product>
    ) : this(id, ownerId, name) {
        this.products.addAll(products)
    }

    val id = StoreroomId(id)
    val ownerId = UserId(ownerId)
    private var products = mutableListOf<Product>()
    private var events = mutableListOf<DomainEvent>()

    companion object {
        const val ZERO_STOCK: Int = 0
    }

    fun products() = products.toList()

    fun events() = events.toList()

    fun addProduct(productId: String, ownerId: String, quantity: Int = ZERO_STOCK): Storeroom =
        findProduct(productId)?.let { product ->
            addProductStock(product, quantity)
        } ?: addNewProduct(productId, ownerId, quantity)

    fun stockOf(productId: String): Int {
        val product = productOf(ProductId(productId))

        return product?.stock() ?: ZERO_STOCK
    }

    fun consumeProduct(productId: String, ownerId: String, quantity: Int): Storeroom =
            findProduct(productId)?.let { product ->
                consumeProductStock(product, quantity)
            } ?: throw ProductDoesNotExitsException(productId)

    private fun addNewProduct(productId: String, ownerId: String, quantity: Int): Storeroom {
        products.add(Product(productId, quantity))
        registerEvent(ProductAdded(productId, this.id.value, this.ownerId.value, quantity))
        return this
    }

    private fun addProductStock(product: Product, quantity: Int): Storeroom {
        replaceProduct(product.addStock(quantity))

        registerEvent(ProductAdded(product.id.value, this.id.value, this.ownerId.value, quantity))

        return this
    }

    private fun consumeProductStock(product: Product, quantity: Int): Storeroom {
        replaceProduct(product.consumeStock(quantity))

        registerEvent(ProductConsumed(product.id.value, this.id.value, this.ownerId.value, quantity))

        if (stockOf(product.id.value) == ZERO_STOCK) {
            registerEvent(ProductSoldOut(product.id.value, id.value, this.ownerId.value))
        }

        return this
    }

    private fun replaceProduct(product: Product) {
        products[products.indexOf(products.find { it.id.value == product.id.value })] = product
    }

    private fun findProduct(productId: String): Product? =
            products.find { it.id.value == productId }

    private fun registerEvent(domainEvent: DomainEvent) {
        this.events.add(domainEvent)
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
