package cabanas.garcia.ismael.storeroom.stepdefs

import cabanas.garcia.ismael.storeroom.application.ApplicationError
import cabanas.garcia.ismael.storeroom.application.productcatalog.createproduct.CreateProductCommand
import cabanas.garcia.ismael.storeroom.application.productcatalog.createproduct.CreateProductCommandHandler
import cabanas.garcia.ismael.storeroom.assertions.that
import cabanas.garcia.ismael.storeroom.domain.productcatalog.ProductDetails
import cabanas.garcia.ismael.storeroom.domain.productcatalog.ProductId
import cabanas.garcia.ismael.storeroom.domain.productcatalog.spi.stubs.InMemoryProductRepository
import io.cucumber.java.en.And
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import org.assertj.core.api.Assertions.assertThat
import java.util.*
import kotlin.test.assertNotNull

class ProductCatalogStepdefs(private val testContext: TestContext,
                             private val inMemoryProductRepository: InMemoryProductRepository,
                             private val createProduct: CreateProductCommandHandler) {

    @Given("^a admin user$")
    fun `a admin user`() {
        testContext.userId = "admin user"
    }

    @And("^he wants the product (.+)$")
    fun `he wants products`(productName : String) {
        val productId = UUID.randomUUID().toString()

        testContext.createProductCommand = CreateProductCommand(productId, testContext.userId, productName)
    }

    @And("^he added the product (.+)$")
    fun `he added products`(productName : String) {
        val productId = UUID.randomUUID().toString()
        val productDetails = ProductDetails(ProductId(productId), productName)

        testContext.createProductCommand = CreateProductCommand(productId, testContext.userId, productName)

        `he adds this product to catalog`()
    }

    @When("^he adds this product to catalog$")
    fun `he adds this product to catalog`() {
        try {
            createProduct.handle(testContext.createProductCommand)
        } catch (e: Exception) {
            testContext.error = e
        }
    }

    @Then("^the product is stored within$")
    fun `the product is stored within`() {
        val product = inMemoryProductRepository.findById(testContext.createProductCommand.productId)
        val adminUser = testContext.userId

        assertNotNull(product, "Product should be stored")
        product.that `corresponds to creator` adminUser
        product.that `has name` product.name
    }

    @Then("^he is notified the product already exist$")
    fun `the product already exist`() {
        assertThat(testContext.error)
                .isNotNull()
                .isInstanceOf(ApplicationError::class.java)
                .hasMessage(String.format("A product already exists with name %s", testContext.createProductCommand.productName))
    }


}