package cabanas.garcia.ismael.storeroom.domain.shoppinglist

import cabanas.garcia.ismael.storeroom.domain.storeroom.spi.InMemoryDatabase

class InMemoryShoppingRepository(private val database: InMemoryDatabase) : ShoppingListRepository {
    override fun findById(shoppingListId: String): ShoppingList? {
        var shoppingList = database.shoppingLists[ShoppingListId(shoppingListId)] ?: throw ShoppingListDoesNotExistException(shoppingListId)

        database.products.forEach { (key, value) -> shoppingList = shoppingList.addProduct(key.value) }

        return shoppingList
    }

    override fun save(shoppingList: ShoppingList) {
        database.shoppingLists[shoppingList.id] = shoppingList
        shoppingList.products().stream().forEach { product -> database.productsShoppingList[product.id] = product }
    }
}