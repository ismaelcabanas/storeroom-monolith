package cabanas.garcia.ismael.storeroom.domain.product

import java.util.UUID

interface ProductRepository {
    fun findById(id: UUID): Product?
    fun findAll(): List<Product>
    fun fetch(id: ProductId): Product?
    fun save(product: Product): Product
}