package cabanas.garcia.ismael.storeroom.infrastructure.database

import cabanas.garcia.ismael.storeroom.domain.shoppinglist.ShoppingList
import cabanas.garcia.ismael.storeroom.domain.shoppinglist.ShoppingListId
import cabanas.garcia.ismael.storeroom.domain.shoppinglist.ProductId as ShoppingListProductId
import cabanas.garcia.ismael.storeroom.domain.shoppinglist.Product as ShoppingListProduct
import cabanas.garcia.ismael.storeroom.domain.storeroom.Product
import cabanas.garcia.ismael.storeroom.domain.storeroom.ProductId
import cabanas.garcia.ismael.storeroom.domain.storeroom.Storeroom
import cabanas.garcia.ismael.storeroom.domain.storeroom.StoreroomId
import cabanas.garcia.ismael.storeroom.domain.productcatalog.ProductId as ProductCatalogId
import cabanas.garcia.ismael.storeroom.domain.productcatalog.Product as ProductCatalog
import java.util.concurrent.ConcurrentHashMap

object InMemoryDatabase {
    fun clean() {
        storerooms.clear()
        products.clear()
        shoppingLists.clear()
        productsShoppingList.clear()
        productsCatalog.clear()
    }

    var storerooms: MutableMap<StoreroomId, Storeroom> = ConcurrentHashMap()
    var products: MutableMap<ProductId, Product> = ConcurrentHashMap()
    var shoppingLists: MutableMap<ShoppingListId, ShoppingList> = ConcurrentHashMap()
    var productsShoppingList: MutableMap<ShoppingListProductId, ShoppingListProduct> = ConcurrentHashMap()
    var productsCatalog: MutableMap<ProductCatalogId, ProductCatalog> = ConcurrentHashMap()
}
