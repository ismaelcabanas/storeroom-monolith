package cabanas.garcia.ismael.storeroom.domain.shoppinglist

import cabanas.garcia.ismael.storeroom.infrastructure.database.InMemoryDatabase

class InMemoryShoppingRepository() : ShoppingListRepository {
    override fun findById(shoppingListId: String): ShoppingList? {
        var shoppingList = InMemoryDatabase.shoppingLists[ShoppingListId(shoppingListId)] ?: throw ShoppingListDoesNotExistException(shoppingListId)

        InMemoryDatabase.products.forEach { (key, value) -> shoppingList = shoppingList.addProduct(key.value) }

        return shoppingList
    }

    override fun save(shoppingList: ShoppingList) {
        InMemoryDatabase.shoppingLists[shoppingList.id] = shoppingList
        shoppingList.products().stream().forEach { product -> InMemoryDatabase.productsShoppingList[product.id] = product }
    }
}