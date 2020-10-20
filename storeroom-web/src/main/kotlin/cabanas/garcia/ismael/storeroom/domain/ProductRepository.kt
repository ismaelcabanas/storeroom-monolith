package cabanas.garcia.ismael.storeroom.domain

import cabanas.garcia.ismael.storeroom.infrastructure.framework.entity.Product
import java.util.UUID

interface ProductRepository {
    fun save(product: Product)
    fun findById(id: UUID): Product?
    fun findAll(): List<Product>
}