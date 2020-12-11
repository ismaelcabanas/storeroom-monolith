package cabanas.garcia.ismael.storeroom.domain.productcatalog.spi.stubs

import cabanas.garcia.ismael.storeroom.domain.productcatalog.Product
import cabanas.garcia.ismael.storeroom.domain.productcatalog.ProductId
import cabanas.garcia.ismael.storeroom.domain.productcatalog.ProductRepository
import java.util.concurrent.ConcurrentHashMap

class InMemoryProductRepository(val products: MutableMap<ProductId, Product> = ConcurrentHashMap()): ProductRepository {

    override fun findById(id: String): Product? {
        return products[ProductId(id)]
    }

    override fun findAll(): List<Product> {
        TODO("Not yet implemented")
    }

    override fun save(product: Product) {
        products[product.id] = product
    }

    override fun findByName(productName: String): Product? {
        return products.values.firstOrNull { product -> product.name == productName }
    }
}