package cabanas.garcia.ismael.storeroom.infrastructure.framework.repository.storeroom.jpa

import cabanas.garcia.ismael.storeroom.domain.storeroom.*
import java.util.*

class JpaStoreroomRepository(
        private val storeroomJpaRepository: SpringJpaStoreroomRepository,
        private val productJpaRepository: SpringJpaStoreroomProductRepository): StoreroomRepository {

    override fun findBy(id: StoreroomId): Storeroom? =
            storeroomJpaRepository.findById(UUID.fromString(id.value))
                    .map { toDomain(it, productJpaRepository.findByStoreroomId(UUID.fromString(id.value))) }
                    .orElse(null)

    override fun save(storeroom: Storeroom) {
        storeroomJpaRepository.save(toEntity(storeroom))
        storeroom.products().forEach { productJpaRepository.save(toEntity(storeroom.id, it)) }
    }

    private fun toEntity(storeroomId: StoreroomId, domain: Product) =
            JpaStoreroomProduct(
                    UUID.fromString(domain.id.value),
                    UUID.fromString(storeroomId.value),
                    domain.stock()
            )

    private fun toEntity(domain: Storeroom) =
            JpaStoreroom(UUID.fromString(domain.id.value), UUID.fromString(domain.ownerId.value), domain.name)

    private fun toDomain(entity: JpaStoreroom, productsEntity: List<JpaStoreroomProduct>): Storeroom? =
            Storeroom(
                    StoreroomId(entity.id.toString()),
                    UserId(entity.ownerId.toString()),
                    entity.name,
                    productsEntity
                            .map { toDomain(it) }
                            .toSet()
            )

    private fun toDomain(entity: JpaStoreroomProduct) =
            Product(ProductId(entity.id.toString()), Stock(entity.stock))
}