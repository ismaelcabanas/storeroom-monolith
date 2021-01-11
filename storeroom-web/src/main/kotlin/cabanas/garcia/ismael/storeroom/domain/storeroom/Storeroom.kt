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

    fun addProduct(productId: String, ownerId: String, quantity: Int = ZERO_STOCK): Storeroom {
        if (productDoesNotExist(productId)) {
            return addNewProduct(productId, ownerId, quantity)
        }

        return addProductStock(productId, ownerId, quantity)
    }

    fun stockOf(productId: String): Int {
        val product = productOf(ProductId(productId))

        return product?.stock() ?: ZERO_STOCK
    }

    fun consumeProduct(productId: String, ownerId: String, quantity: Int): Storeroom {
        if (productDoesNotExist(productId)) {
            throw ProductDoesNotExitsException(productId)
        }

        return consumeProductStock(productId, ownerId, quantity)
    }

    private fun productDoesNotExist(productId: String): Boolean =
            !products.any { it.id.value == productId }

    private fun addNewProduct(productId: String, ownerId: String, quantity: Int): Storeroom {
        products.add(Product(productId, quantity))
        registerEvent(ProductAdded(productId, this.id.value, this.ownerId.value, quantity))
        return this
    }

    private fun addProductStock(productId: String, ownerId: String, quantity: Int): Storeroom {
        val product = findProduct(productId)

        products[products.indexOf(products.find { it.id.value == productId })] = product.addStock(quantity)

        registerEvent(ProductAdded(productId, this.id.value, this.ownerId.value, quantity))

        return this
    }

    private fun consumeProductStock(productId: String, ownerId: String, quantity: Int): Storeroom {
        val product = findProduct(productId)

        products[products.indexOf(products.find { it.id.value == productId })] = product.consumeStock(quantity)

        registerEvent(ProductConsumed(productId, this.id.value, this.ownerId.value, quantity))

        if (stockOf(productId) == ZERO_STOCK) {
            registerEvent(ProductSoldOut(productId, id.value, this.ownerId.value))
        }

        return this
    }

    private fun findProduct(productId: String): Product =
            products.find { it.id.value == productId }!!

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
