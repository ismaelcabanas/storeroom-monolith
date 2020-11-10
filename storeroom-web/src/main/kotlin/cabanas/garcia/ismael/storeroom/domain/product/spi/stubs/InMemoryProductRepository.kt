package cabanas.garcia.ismael.storeroom.domain.product.spi.stubs

import cabanas.garcia.ismael.storeroom.domain.product.Product
import cabanas.garcia.ismael.storeroom.domain.product.ProductId
import cabanas.garcia.ismael.storeroom.domain.product.ProductRepository
import java.util.*

class InMemoryProductRepository(val products: MutableMap<ProductId, Product> = HashMap()): ProductRepository {

    override fun findById(id: UUID): Product? {
        TODO("Not yet implemented")
    }

    override fun findAll(): List<Product> {
        TODO("Not yet implemented")
    }

    override fun save(product: Product): Product {
        products[product.id] = product
        return product
    }

    override fun findByName(productName: String): Product? {
        return products.values.firstOrNull { product -> product.name == productName }
    }
}