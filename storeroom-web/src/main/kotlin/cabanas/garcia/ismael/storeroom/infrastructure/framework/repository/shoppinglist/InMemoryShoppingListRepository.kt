package cabanas.garcia.ismael.storeroom.infrastructure.framework.repository.shoppinglist

import cabanas.garcia.ismael.storeroom.domain.shoppinglist.ShoppingList
import cabanas.garcia.ismael.storeroom.domain.shoppinglist.ShoppingListDoesNotExistException
import cabanas.garcia.ismael.storeroom.domain.shoppinglist.ShoppingListId
import cabanas.garcia.ismael.storeroom.domain.shoppinglist.ShoppingListRepository
import cabanas.garcia.ismael.storeroom.infrastructure.database.InMemoryDatabase

class InMemoryShoppingListRepository : ShoppingListRepository {
    override fun findBy(id: ShoppingListId): ShoppingList? {
        var shoppingList = InMemoryDatabase.shoppingLists[id] ?: throw ShoppingListDoesNotExistException(id)

        InMemoryDatabase.productsShoppingList.forEach { (key, value) -> shoppingList = shoppingList.addProduct(key.value, value.name) }

        return shoppingList
    }

    override fun save(shoppingList: ShoppingList) {
        InMemoryDatabase.shoppingLists[shoppingList.id] = shoppingList
        shoppingList.products().stream().forEach { product -> InMemoryDatabase.productsShoppingList[product.id] = product }
    }
}