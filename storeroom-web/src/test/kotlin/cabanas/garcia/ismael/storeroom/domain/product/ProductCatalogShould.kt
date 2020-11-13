package cabanas.garcia.ismael.storeroom.domain.product

import cabanas.garcia.ismael.storeroom.application.product.createproduct.CreateProductCommand
import cabanas.garcia.ismael.storeroom.application.product.createproduct.CreateProductCommandHandler
import cabanas.garcia.ismael.storeroom.assertions.that
import cabanas.garcia.ismael.storeroom.domain.product.spi.stubs.InMemoryProductRepository
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertNotNull

internal class ProductCatalogShould {

    private lateinit var productRepository: InMemoryProductRepository

    @BeforeEach
    fun `setUp`() {
        productRepository = InMemoryProductRepository()
    }

    @Test
    fun `add new product to catalog`() {
        val productCreator = ProductCreator(productRepository)
        val sut = CreateProductCommandHandler(productCreator)

        sut.handle(command = CreateProductCommand("some product Id", "some id", "Botella de leche"))

        assertThatProductIsSaved("some id", "some product Id", "Botella de leche")
    }

    @Test
    fun `throw already product exist exception when add a product with the same name`() {
        val productCreator = ProductCreator(productRepository)
        val sut = CreateProductCommandHandler(productCreator)
        val command = CreateProductCommand("some product Id", "some id", "Botella de leche")
        sut.handle(command)

        val throwable = Assertions.catchThrowable { sut.handle(command) }

        assertThat(throwable).isInstanceOf(ProductAlreadyExistsException::class.java)
    }

    private fun assertThatProductIsSaved(userId: String, productId: String, productName: String) {
        val product = productRepository.products[ProductId(productId)]

        assertNotNull(product, "Product should not be null")

        product.that `has id` productId
        product.that `corresponds to creator` userId
        product.that `has name` productName
    }
}