package cabanas.garcia.ismael.storeroom.infrastructure.framework.repository.productcatalog.jpa

import cabanas.garcia.ismael.storeroom.domain.productcatalog.Product
import cabanas.garcia.ismael.storeroom.domain.productcatalog.ProductId
import cabanas.garcia.ismael.storeroom.domain.productcatalog.ProductRepository
import cabanas.garcia.ismael.storeroom.domain.productcatalog.UserId
import java.util.UUID
import java.util.Optional

class JpaProductRepository(private val springJpaRepository: SpringJpaProductRepository): ProductRepository {
    override fun findById(id: String): Product? =
            toDomain(springJpaRepository.findById(UUID.fromString(id)))

    override fun findAll(): List<Product> =
        springJpaRepository.findAll().map { entity -> toDomain(entity) }

    override fun save(product: Product) {
        springJpaRepository.save(toEntity(product))
    }

    override fun findByName(productName: String): Product? =
        springJpaRepository.findByName(productName)?.let { toDomain(it) }

    private fun toDomain(entity: Optional<JpaProduct>): Product? {
        return if (!entity.isPresent) {
            null
        } else {
            toDomain(entity.get())
        }
    }

    private fun toDomain(entity: JpaProduct): Product =
        Product(ProductId(entity.id.toString()), UserId(entity.creatorId.toString()), entity.name)


    private fun toEntity(product: Product) =
            JpaProduct(UUID.fromString(product.id.value), UUID.fromString(product.creatorId.value), product.name)
    /*
    override fun save(product: Product) {
        springJpaRepository.save(product)
    }

    override fun findById(id: UUID): Product? {
        return springJpaRepository.findByIdOrNull(id)
    }

    override fun findAll(): List<Product> {
        return StreamSupport
                .stream(springJpaRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }
    */

}