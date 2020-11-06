package cabanas.garcia.ismael.storeroom.domain.product

import java.util.UUID

interface ProductRepository {
    fun findById(id: UUID): Product?
    fun findAll(): List<Product>
    fun save(product: Product): Product
    fun findByName(productName: String): Product?
}