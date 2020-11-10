package cabanas.garcia.ismael.storeroom.domain.product

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
        val sut = ProductCreator(productRepository)
        val userId = UserId("some id")
        val productDetails = ProductDetails(ProductId("some product Id"), "Botella de leche")

        sut.byUserWithDetails(userId, productDetails)

        assertThatProductIsSaved("some id", "some product Id", "Botella de leche")
    }

    @Test
    fun `throw already product exist exception when add a product with the same name`() {
        val sut = ProductCreator(productRepository)
        val userId = UserId("some id")
        val productDetails = ProductDetails(ProductId("some product Id"), "Botella de leche")
        sut.byUserWithDetails(userId, productDetails)

        val throwable = Assertions.catchThrowable { sut.byUserWithDetails(userId, productDetails) }

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