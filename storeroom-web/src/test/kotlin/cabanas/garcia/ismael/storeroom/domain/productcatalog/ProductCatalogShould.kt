package cabanas.garcia.ismael.storeroom.domain.productcatalog

import cabanas.garcia.ismael.storeroom.application.ApplicationError
import cabanas.garcia.ismael.storeroom.application.productcatalog.createproduct.CreateProductCommand
import cabanas.garcia.ismael.storeroom.application.productcatalog.createproduct.CreateProductCommandHandler
import cabanas.garcia.ismael.storeroom.cucumber.assertions.that
import cabanas.garcia.ismael.storeroom.domain.productcatalog.api.CreateProduct
import cabanas.garcia.ismael.storeroom.domain.productcatalog.spi.stubs.InMemoryProductRepository
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertNotNull

internal class ProductCatalogShould {

    private lateinit var productRepository: InMemoryProductRepository
    private lateinit var productCreator: CreateProduct

    @BeforeEach
    fun `setUp`() {
        productRepository = InMemoryProductRepository()
        productCreator = ProductCreator(productRepository)
    }

    @Test
    fun `add new product to catalog`() {
        val sut = CreateProductCommandHandler(productCreator)
        val command = CreateProductCommand("some product Id", "some id", "Botella de leche")

        sut.handle(command)

        assertThatProductIsSaved("some id", "some product Id", "Botella de leche")
    }

    @Test
    fun `throw exception when add new product with a name that already exist`() {
        val sut = CreateProductCommandHandler(productCreator)
        val command = CreateProductCommand("some product Id", "some id", "Botella de leche")
        sut.handle(command)

        val throwable = Assertions.catchThrowable { sut.handle(command) }

        assertThat(throwable).isInstanceOf(ApplicationError::class.java)
    }

    private fun assertThatProductIsSaved(userId: String, productId: String, productName: String) {
        val product = productRepository.products[ProductId(productId)]

        assertNotNull(product, "Product should not be null")

        product.that `has id` productId
        product.that `corresponds to creator` userId
        product.that `has name` productName
    }
}