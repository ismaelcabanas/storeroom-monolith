package cabanas.garcia.ismael.storeroom.domain.storeroom.spi

import cabanas.garcia.ismael.storeroom.domain.storeroom.Storeroom
import cabanas.garcia.ismael.storeroom.domain.storeroom.StoreroomDoesNotExistException
import cabanas.garcia.ismael.storeroom.domain.storeroom.StoreroomId
import cabanas.garcia.ismael.storeroom.domain.storeroom.StoreroomRepository
import java.util.concurrent.ConcurrentHashMap

class InMemoryStoreroomRepository(private val database: InMemoryStoreroomDatabase): StoreroomRepository {
    override fun findById(id: String): Storeroom {
        return database.storerooms[StoreroomId(id)] ?: throw StoreroomDoesNotExistException(id)
    }

    override fun save(storeroom: Storeroom) {
        database.storerooms[storeroom.id] = storeroom
        storeroom.products.stream().forEach { product -> database.products[product.id] = product }
    }
}