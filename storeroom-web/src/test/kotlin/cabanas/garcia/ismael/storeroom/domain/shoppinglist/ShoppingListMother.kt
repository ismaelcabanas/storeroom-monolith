package cabanas.garcia.ismael.storeroom.domain.shoppinglist

import java.util.UUID

class ShoppingListMother {
    companion object {
        fun createShoppingListWithProducts(products: List<Product>): ShoppingList {
            return ShoppingList(
                    UUID.randomUUID().toString(),
                    UUID.randomUUID().toString(),
                    UUID.randomUUID().toString(),
                    products)
        }
    }
}