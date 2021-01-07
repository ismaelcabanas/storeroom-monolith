package cabanas.garcia.ismael.storeroom.domain.shoppinglist

class ShoppingList(
        val id: ShoppingListId,
        val storeroomId: StoreroomId,
        val ownerId: UserId,
        private var products: List<Product> = listOf()) {

    fun products() = products

    fun addProduct(product: Product): ShoppingList {
        if (productOf(product.id) != null) {
            throw ProductAlreadyExitsException(product.id)
        }
        return ShoppingList(id, storeroomId, ownerId, products.plus(product))
    }

    fun productBought(productId: String): Boolean {
        val product = productOf(ProductId(productId)) ?: throw ProductDoesNotExitsException(productId)

        return product.bought
    }

    companion object {
        fun create(shoppingListId: String, storeroomId: String, ownerId: String): ShoppingList =
                ShoppingList(ShoppingListId(shoppingListId), StoreroomId(storeroomId), UserId(ownerId), emptyList())
    }

    private fun productOf(productId: ProductId): Product? = products.find { product -> product.id == productId }
}