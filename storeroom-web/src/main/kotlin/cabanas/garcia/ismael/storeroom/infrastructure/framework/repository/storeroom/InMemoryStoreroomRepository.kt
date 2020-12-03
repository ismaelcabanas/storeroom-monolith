package cabanas.garcia.ismael.storeroom.infrastructure.framework.repository.storeroom

import cabanas.garcia.ismael.storeroom.domain.storeroom.Storeroom
import cabanas.garcia.ismael.storeroom.domain.storeroom.exception.StoreroomDoesNotExistException
import cabanas.garcia.ismael.storeroom.domain.storeroom.StoreroomId
import cabanas.garcia.ismael.storeroom.domain.storeroom.StoreroomRepository
import cabanas.garcia.ismael.storeroom.infrastructure.database.InMemoryDatabase

class InMemoryStoreroomRepository: StoreroomRepository {

    override fun findById(id: String): Storeroom {
        var storeroom = InMemoryDatabase.storerooms[StoreroomId(id)] ?: throw StoreroomDoesNotExistException(id)

        InMemoryDatabase.products.forEach { (key, value) -> storeroom = storeroom.addProduct(key.value, storeroom.ownerId.value, value.stock.value) }

        return storeroom
    }

    override fun save(storeroom: Storeroom) {
        InMemoryDatabase.storerooms[storeroom.id] = storeroom
        storeroom.products().stream().forEach { product -> InMemoryDatabase.products[product.id] = product }
    }

    fun clean() {
        InMemoryDatabase.clean()
    }
}