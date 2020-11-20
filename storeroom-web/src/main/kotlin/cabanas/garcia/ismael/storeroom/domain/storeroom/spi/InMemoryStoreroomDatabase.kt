package cabanas.garcia.ismael.storeroom.domain.storeroom.spi

import cabanas.garcia.ismael.storeroom.domain.storeroom.Product
import cabanas.garcia.ismael.storeroom.domain.storeroom.ProductId
import cabanas.garcia.ismael.storeroom.domain.storeroom.Storeroom
import cabanas.garcia.ismael.storeroom.domain.storeroom.StoreroomId
import java.util.concurrent.ConcurrentHashMap

class InMemoryStoreroomDatabase(
        val storerooms: MutableMap<StoreroomId, Storeroom> = ConcurrentHashMap(),
        val products: MutableMap<ProductId, Product> = ConcurrentHashMap()) {
}