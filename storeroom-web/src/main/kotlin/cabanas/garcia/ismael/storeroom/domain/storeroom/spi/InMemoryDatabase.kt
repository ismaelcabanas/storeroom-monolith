package cabanas.garcia.ismael.storeroom.domain.storeroom.spi

import cabanas.garcia.ismael.storeroom.domain.shoppinglist.ShoppingList
import cabanas.garcia.ismael.storeroom.domain.shoppinglist.ShoppingListId
import cabanas.garcia.ismael.storeroom.domain.storeroom.Product
import cabanas.garcia.ismael.storeroom.domain.storeroom.ProductId
import cabanas.garcia.ismael.storeroom.domain.storeroom.Storeroom
import cabanas.garcia.ismael.storeroom.domain.storeroom.StoreroomId
import java.util.concurrent.ConcurrentHashMap

class InMemoryDatabase(
        val storerooms: MutableMap<StoreroomId, Storeroom> = ConcurrentHashMap(),
        val products: MutableMap<ProductId, Product> = ConcurrentHashMap(),
        val shoppingLists: MutableMap<ShoppingListId, ShoppingList> = ConcurrentHashMap(),
        val productsShoppingList: MutableMap<cabanas.garcia.ismael.storeroom.domain.shoppinglist.ProductId, cabanas.garcia.ismael.storeroom.domain.shoppinglist.Product> = ConcurrentHashMap())