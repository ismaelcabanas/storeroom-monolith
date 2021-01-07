package cabanas.garcia.ismael.storeroom.infrastructure.framework.repository.shoppinglist

import cabanas.garcia.ismael.storeroom.domain.shoppinglist.*
import cabanas.garcia.ismael.storeroom.infrastructure.database.InMemoryDatabase

class InMemoryShoppingListRepository : ShoppingListRepository {
    override fun findBy(id: ShoppingListId): ShoppingList? {
        var shoppingList: ShoppingList? = InMemoryDatabase.shoppingLists[id] ?: return null

        InMemoryDatabase.productsShoppingList.forEach { (key, value) -> shoppingList = shoppingList?.addProduct(Product(key, value.name)) }

        return shoppingList
    }

    override fun findBy(id: StoreroomId): ShoppingList? {
        var shoppingList = InMemoryDatabase.shoppingLists.values
                .find { it.storeroomId == id }

        InMemoryDatabase.productsShoppingList.forEach { (key, value) -> shoppingList = shoppingList?.addProduct(Product(key, value.name)) }

        return shoppingList
    }

    override fun save(shoppingList: ShoppingList) {
        InMemoryDatabase.shoppingLists[shoppingList.id] = shoppingList
        shoppingList.products().stream().forEach { product -> InMemoryDatabase.productsShoppingList[product.id] = product }
    }
}