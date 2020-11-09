package cabanas.garcia.ismael.storeroom.stepdefs

import cabanas.garcia.ismael.storeroom.assertions.that
import cabanas.garcia.ismael.storeroom.domain.api.CreateProduct
import cabanas.garcia.ismael.storeroom.domain.product.ProductAlreadyExistsException
import cabanas.garcia.ismael.storeroom.domain.product.ProductDetails
import cabanas.garcia.ismael.storeroom.domain.product.ProductId
import cabanas.garcia.ismael.storeroom.domain.product.UserId
import io.cucumber.java.en.And
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import org.assertj.core.api.Assertions.assertThat
import java.util.*

class ProductCatalogStepdefs(private val testContext: TestContext,
                             private val createProduct: CreateProduct) {

    @Given("^a admin user$")
    fun `a admin user`() {
        testContext.userId = "admin user"
    }

    @And("^he wants the product (.+)$")
    fun `he wants products`(productName : String) {
        val productId = UUID.randomUUID().toString()
        val productDetails = ProductDetails(ProductId(productId), productName)

        testContext.requestedProductDetails = productDetails
    }

    @And("^he added the product (.+)$")
    fun `he added products`(productName : String) {
        val productId = UUID.randomUUID().toString()
        val productDetails = ProductDetails(ProductId(productId), productName)

        testContext.requestedProductDetails = productDetails

        `he adds this product to catalog`()
    }

    @When("^he adds this product to catalog$")
    fun `he adds this product to catalog`() {
        try {
            testContext.createdProduct = createProduct.byUserWithDetails(UserId(testContext.userId), testContext.requestedProductDetails)
        } catch (e: Exception) {
            testContext.error = e
        }
    }

    @Then("^the product is stored within$")
    fun `the product is stored within`() {
        val product = testContext.createdProduct
        val adminUser = testContext.userId

        product.that `corresponds to creator` adminUser
        product.that `has name` product.name
    }

    @Then("^he is notified the product already exist$")
    fun `the product already exist`() {
        assertThat(testContext.error)
                .isNotNull()
                .isInstanceOf(ProductAlreadyExistsException::class.java)
                .hasMessage(String.format("A product already exists with name %s", testContext.requestedProductDetails.name))
    }


}