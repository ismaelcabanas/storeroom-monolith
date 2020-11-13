package cabanas.garcia.ismael.storeroom.domain.productcatalog

interface ProductRepository {
    fun findById(id: String): Product?
    fun findAll(): List<Product>
    fun save(product: Product): Product
    fun findByName(productName: String): Product?
}