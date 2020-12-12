package cabanas.garcia.ismael.storeroom.infrastructure.framework.repository.storeroom.jpa

import cabanas.garcia.ismael.storeroom.domain.storeroom.Storeroom
import cabanas.garcia.ismael.storeroom.domain.storeroom.StoreroomId
import cabanas.garcia.ismael.storeroom.domain.storeroom.StoreroomRepository
import cabanas.garcia.ismael.storeroom.domain.storeroom.UserId
import java.util.*

class JpaStoreroomRepository(private val springJpaRepository: SpringJpaStoreroomRepository): StoreroomRepository {
    override fun findById(id: String): Storeroom? =
            springJpaRepository.findById(UUID.fromString(id)).map { toDomain(it) }.orElse(null)

    override fun save(storeroom: Storeroom) {
        springJpaRepository.save(toEntity(storeroom))
    }

    private fun toDomain(entity: JpaStoreroom): Storeroom? =
            Storeroom(StoreroomId(entity.id.toString()), UserId(entity.ownerId.toString()), entity.name)

    private fun toEntity(domain: Storeroom) =
            JpaStoreroom(UUID.fromString(domain.id.value), UUID.fromString(domain.ownerId.value), domain.name)
}