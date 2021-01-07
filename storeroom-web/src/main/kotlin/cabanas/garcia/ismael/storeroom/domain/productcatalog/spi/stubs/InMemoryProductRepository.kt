package cabanas.garcia.ismael.storeroom.domain.productcatalog.spi.stubs

import cabanas.garcia.ismael.storeroom.domain.productcatalog.Product
import cabanas.garcia.ismael.storeroom.domain.productcatalog.ProductId
import cabanas.garcia.ismael.storeroom.domain.productcatalog.ProductRepository
import cabanas.garcia.ismael.storeroom.infrastructure.database.InMemoryDatabase
import java.util.concurrent.ConcurrentHashMap

class InMemoryProductRepository: ProductRepository {

    override fun findById(id: String): Product? {
        return InMemoryDatabase.productsCatalog
                .filterKeys { it == ProductId(id) }[ProductId(id)]
    }

    override fun findAll(): List<Product> {
        TODO("Not yet implemented")
    }

    override fun save(product: Product) {
        InMemoryDatabase.productsCatalog[product.id] = product
    }

    override fun findByName(productName: String): Product? =
            InMemoryDatabase.productsCatalog.values
                    .find { it.name == productName }
}