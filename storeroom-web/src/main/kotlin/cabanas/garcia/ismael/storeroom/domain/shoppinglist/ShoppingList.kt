package cabanas.garcia.ismael.storeroom.domain.shoppinglist

class ShoppingList internal constructor(
        val id: ShoppingListId,
        val storeroomId: StoreroomId,
        val ownerId: UserId,
        val products: List<Product>) {

    fun addProduct(productId: String): ShoppingList {
        val product = productOf(ProductId(productId))

        if (product != null) {
            throw ProductAlreadyExitsException(productId)
        }

        return ShoppingList(id, storeroomId, ownerId, updateProducts(Product(ProductId(productId))))
    }

    fun productBought(productId: String): Boolean {
        val product = productOf(ProductId(productId)) ?: throw ProductDoesNotExitsException(productId)

        return product.bought
    }

    companion object {
        fun create(shoppingListId: String, storeroomId: String, ownerId: String): ShoppingList {
            return ShoppingList(ShoppingListId(shoppingListId), StoreroomId(storeroomId), UserId(ownerId), emptyList())
        }
    }

    private fun productOf(productId: ProductId): Product? {
        return products.find { product -> product.id == productId }
    }

    private fun updateProducts(product: Product): List<Product> {
        val currentProducts = products.toMutableList()
        currentProducts.add(product)

        return currentProducts
    }
}