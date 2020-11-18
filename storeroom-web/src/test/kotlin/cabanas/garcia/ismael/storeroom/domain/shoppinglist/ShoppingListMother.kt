package cabanas.garcia.ismael.storeroom.domain.shoppinglist

import java.util.*

class ShoppingListMother {
    companion object {
        fun createShoppingListWithProducts(products: List<Product>): ShoppingList {
            return ShoppingList(
                    ShoppingListId(UUID.randomUUID().toString()),
                    StoreroomId((UUID.randomUUID().toString())),
                    UserId(UUID.randomUUID().toString()),
                    products)
        }
    }
}