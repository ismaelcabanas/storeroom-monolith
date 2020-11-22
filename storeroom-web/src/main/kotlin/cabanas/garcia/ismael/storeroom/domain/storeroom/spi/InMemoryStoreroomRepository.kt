package cabanas.garcia.ismael.storeroom.domain.storeroom.spi

import cabanas.garcia.ismael.storeroom.domain.storeroom.Storeroom
import cabanas.garcia.ismael.storeroom.domain.storeroom.exception.StoreroomDoesNotExistException
import cabanas.garcia.ismael.storeroom.domain.storeroom.StoreroomId
import cabanas.garcia.ismael.storeroom.domain.storeroom.StoreroomRepository
import java.util.concurrent.ConcurrentHashMap

class InMemoryStoreroomRepository(private val database: InMemoryStoreroomDatabase): StoreroomRepository {
    override fun findById(id: String): Storeroom {
        var storeroom = database.storerooms[StoreroomId(id)] ?: throw StoreroomDoesNotExistException(id)

        database.products.forEach { (key, value) -> storeroom = storeroom.addProduct(key.value, storeroom.ownerId.value, value.stock.value) }

        return storeroom
    }

    override fun save(storeroom: Storeroom) {
        database.storerooms[storeroom.id] = storeroom
        storeroom.products().stream().forEach { product -> database.products[product.id] = product }
    }
}