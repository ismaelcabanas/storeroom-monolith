package cabanas.garcia.ismael.storeroom.domain.storeroom.spi

import cabanas.garcia.ismael.storeroom.domain.storeroom.Storeroom
import cabanas.garcia.ismael.storeroom.domain.storeroom.StoreroomFactory
import cabanas.garcia.ismael.storeroom.domain.storeroom.StoreroomId
import cabanas.garcia.ismael.storeroom.domain.storeroom.UserId

class DefaultStoreroomFactory: StoreroomFactory {
    override fun create(storeroomId: String, ownerId: String, storeroomName: String): Storeroom {
        return Storeroom(storeroomId, ownerId, storeroomName)
    }

}
