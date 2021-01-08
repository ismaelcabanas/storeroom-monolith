package cabanas.garcia.ismael.storeroom.domain.shoppinglist

class ShoppingList(
        id: String,
        storeroomId: String,
        ownerId: String,
        private var products: List<Product> = listOf()) {

    val id: ShoppingListId = ShoppingListId(id)
    val storeroomId: StoreroomId = StoreroomId(storeroomId)
    val ownerId: UserId = UserId(ownerId)

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

    companion object {
        fun create(shoppingListId: String, storeroomId: String, ownerId: String): ShoppingList =
                ShoppingList(shoppingListId, storeroomId, ownerId, emptyList())
    }

    private fun productOf(productId: ProductId): Product? = products.find { product -> product.id == productId }
}