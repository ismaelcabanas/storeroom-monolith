package cabanas.garcia.ismael.storeroom.infrastructure.framework.repository.storeroom.jpa

import cabanas.garcia.ismael.storeroom.domain.storeroom.Storeroom
import cabanas.garcia.ismael.storeroom.domain.storeroom.StoreroomId
import cabanas.garcia.ismael.storeroom.domain.storeroom.StoreroomRepository
import cabanas.garcia.ismael.storeroom.domain.storeroom.UserId
import cabanas.garcia.ismael.storeroom.infrastructure.framework.repository.storeroom.jpa.entity.StoreroomEntity
import cabanas.garcia.ismael.storeroom.infrastructure.framework.repository.storeroom.jpa.spring.SpringJpaStoreroomRepository
import java.util.*

class JpaStoreroomRepository(private val springJpaRepository: SpringJpaStoreroomRepository): StoreroomRepository {
    override fun findById(id: String): Storeroom? =
            toDomain(springJpaRepository.findById(UUID.fromString(id)))

    override fun save(storeroom: Storeroom) {
        springJpaRepository.save(toEntity(storeroom))
    }

    private fun toDomain(entity: Optional<StoreroomEntity>): Storeroom? {
        if (entity.isPresent) {
            return Storeroom(StoreroomId(entity.get().id.toString()), UserId(entity.get().ownerId.toString()), entity.get().name)
        }

        return null
    }

    private fun toEntity(domain: Storeroom) =
            StoreroomEntity(UUID.fromString(domain.id.value), UUID.fromString(domain.ownerId.value), domain.name)
}