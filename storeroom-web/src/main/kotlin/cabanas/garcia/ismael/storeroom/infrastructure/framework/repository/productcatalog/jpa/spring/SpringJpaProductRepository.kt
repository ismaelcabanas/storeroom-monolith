package cabanas.garcia.ismael.storeroom.infrastructure.framework.repository.productcatalog.jpa.spring

import cabanas.garcia.ismael.storeroom.infrastructure.framework.repository.productcatalog.jpa.entity.ProductEntity
import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface SpringJpaProductRepository : CrudRepository<ProductEntity, UUID> {
    fun findByName(productName: String): ProductEntity?

}