package cabanas.garcia.ismael.storeroom.stepdefs

import cabanas.garcia.ismael.storeroom.assertions.that
import cabanas.garcia.ismael.storeroom.domain.api.CreateProduct
import cabanas.garcia.ismael.storeroom.domain.product.ProductDetails
import cabanas.garcia.ismael.storeroom.domain.product.ProductId
import cabanas.garcia.ismael.storeroom.domain.product.UserId
import io.cucumber.java.en.And
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
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

    @When("^he adds this product to catalog$")
    fun `he adds this product to catalog`() {
        testContext.createdProduct = createProduct.byUserWithDetails(UserId(testContext.userId), testContext.requestedProductDetails)
    }

    @Then("^the product is stored within$")
    fun `the product is stored within`() {
        val product = testContext.createdProduct
        val adminUser = testContext.userId

        product.that `corresponds to user` adminUser
        product.that `has name` product.name
    }


}