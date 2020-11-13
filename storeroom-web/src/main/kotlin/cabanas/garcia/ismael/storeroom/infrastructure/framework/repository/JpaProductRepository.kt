package cabanas.garcia.ismael.storeroom.infrastructure.framework.repository

import cabanas.garcia.ismael.storeroom.domain.product.ProductRepository

class JpaProductRepository(private val springJpaRepository: SpringJpaProductRepository): ProductRepository {
    override fun findById(id: String): cabanas.garcia.ismael.storeroom.domain.product.Product? {
        TODO("Not yet implemented")
    }

    override fun findAll(): List<cabanas.garcia.ismael.storeroom.domain.product.Product> {
        TODO("Not yet implemented")
    }

    override fun save(product: cabanas.garcia.ismael.storeroom.domain.product.Product): cabanas.garcia.ismael.storeroom.domain.product.Product {
        TODO("Not yet implemented")
    }

    override fun findByName(productName: String): cabanas.garcia.ismael.storeroom.domain.product.Product? {
        TODO("Not yet implemented")
    }

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