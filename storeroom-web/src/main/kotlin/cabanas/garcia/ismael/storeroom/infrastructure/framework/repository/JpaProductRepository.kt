package cabanas.garcia.ismael.storeroom.infrastructure.framework.repository

import cabanas.garcia.ismael.storeroom.domain.ProductRepository
import cabanas.garcia.ismael.storeroom.infrastructure.framework.entity.Product
import org.springframework.data.repository.findByIdOrNull
import java.util.UUID
import java.util.stream.Collectors
import java.util.stream.StreamSupport

class JpaProductRepository(private val springJpaRepository: SpringJpaProductRepository): ProductRepository {
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
}