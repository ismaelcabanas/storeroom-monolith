package cabanas.garcia.ismael.storeroom.infrastructure.framework.repository

import cabanas.garcia.ismael.storeroom.infrastructure.framework.entity.Product
import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface SpringJpaProductRepository : CrudRepository<Product, UUID> {

}