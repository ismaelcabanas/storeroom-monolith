package cabanas.garcia.ismael.storeroom.infrastructure.database

import cabanas.garcia.ismael.storeroom.domain.shoppinglist.ShoppingList
import cabanas.garcia.ismael.storeroom.domain.shoppinglist.ShoppingListId
import cabanas.garcia.ismael.storeroom.domain.storeroom.Product
import cabanas.garcia.ismael.storeroom.domain.storeroom.ProductId
import cabanas.garcia.ismael.storeroom.domain.storeroom.Storeroom
import cabanas.garcia.ismael.storeroom.domain.storeroom.StoreroomId
import java.util.concurrent.ConcurrentHashMap

object InMemoryDatabase {
    fun clean() {
        storerooms.clear()
        products.clear()
        shoppingLists.clear()
        productsShoppingList.clear()
    }

    var storerooms: MutableMap<StoreroomId, Storeroom> = ConcurrentHashMap()
    var products: MutableMap<ProductId, Product> = ConcurrentHashMap()
    var shoppingLists: MutableMap<ShoppingListId, ShoppingList> = ConcurrentHashMap()
    var productsShoppingList: MutableMap<cabanas.garcia.ismael.storeroom.domain.shoppinglist.ProductId, cabanas.garcia.ismael.storeroom.domain.shoppinglist.Product> = ConcurrentHashMap()
}
