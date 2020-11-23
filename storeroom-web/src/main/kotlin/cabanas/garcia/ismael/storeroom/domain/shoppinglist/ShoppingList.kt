package cabanas.garcia.ismael.storeroom.domain.shoppinglist

class ShoppingList internal constructor(
        val id: ShoppingListId,
        val storeroomId: StoreroomId,
        val ownerId: UserId,
        private var products: List<Product> = listOf()) {

    fun addProduct(productId: String): ShoppingList {
        val product = productOf(ProductId(productId))

        if (product != null) {
            throw ProductAlreadyExitsException(productId)
        }

        products = products.plus(Product(ProductId((productId))))

        return this
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

    private fun updateProducts(product: Product): List<Product> {
        val currentProducts = products.toMutableList()
        currentProducts.add(product)

        return currentProducts
    }
}