package cabanas.garcia.ismael.storeroom.domain.spi.stubs

import cabanas.garcia.ismael.storeroom.domain.product.Product
import cabanas.garcia.ismael.storeroom.domain.product.ProductId
import cabanas.garcia.ismael.storeroom.domain.product.ProductRepository
import java.util.*

class InMemoryProductRepository(private val products: MutableMap<ProductId, Product> = HashMap()): ProductRepository {

    override fun findById(id: UUID): Product? {
        TODO("Not yet implemented")
    }

    override fun findAll(): List<Product> {
        TODO("Not yet implemented")
    }

    override fun fetch(id: ProductId): Product? {
        return products[id]
    }

    override fun save(product: Product): Product {
        products[product.id] = product
        return product
    }
}