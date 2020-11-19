package cabanas.garcia.ismael.storeroom.domain.storeroom.spi

import cabanas.garcia.ismael.storeroom.domain.storeroom.Storeroom
import cabanas.garcia.ismael.storeroom.domain.storeroom.StoreroomId
import cabanas.garcia.ismael.storeroom.domain.storeroom.StoreroomRepository
import java.util.concurrent.ConcurrentHashMap

class InMemoryStoreroomRepository(private val storerooms: MutableMap<StoreroomId, Storeroom> = ConcurrentHashMap()): StoreroomRepository {
    override fun findById(id: String): Storeroom? {
        return storerooms[StoreroomId(id)]
    }

    override fun save(storeroom: Storeroom) {
        storerooms[storeroom.id] = storeroom
    }
}