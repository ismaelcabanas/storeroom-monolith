package cabanas.garcia.ismael.storeroom.domain.shoppinglist

class ShoppingList(
        id: String,
        storeroomId: String,
        ownerId: String,
        products: List<Product> = emptyList()) {

    val id: ShoppingListId = ShoppingListId(id)
    val storeroomId: StoreroomId = StoreroomId(storeroomId)
    val ownerId: UserId = UserId(ownerId)
    val products: List<Product> = products

    companion object {
        fun create(shoppingListId: String, storeroomId: String, ownerId: String): ShoppingList =
                ShoppingList(shoppingListId, storeroomId, ownerId)
    }

    fun products() = products

    fun addProduct(product: Product): ShoppingList {
        if (productOf(product.id) != null) {
            throw ProductAlreadyExitsException(product.id)
        }
        return ShoppingList(id.value, storeroomId.value, ownerId.value, products.plus(product))
    }

    fun productBought(productId: String): Boolean {
        val product = productOf(ProductId(productId)) ?: throw ProductDoesNotExitsException(productId)

        return product.bought
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ShoppingList

        if (id != other.id) return false
        if (storeroomId != other.storeroomId) return false
        if (ownerId != other.ownerId) return false
        if (products != other.products) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + storeroomId.hashCode()
        result = 31 * result + ownerId.hashCode()
        result = 31 * result + products.hashCode()
        return result
    }

    private fun productOf(productId: ProductId): Product? = products.find { product -> product.id == productId }
}