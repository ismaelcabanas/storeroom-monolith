package cabanas.garcia.ismael.storeroom.cucumber.stepdefs

import cabanas.garcia.ismael.storeroom.cucumber.assertions.that
import cabanas.garcia.ismael.storeroom.domain.storeroom.StoreroomFactory
import io.cucumber.java.en.And
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import org.assertj.core.api.Assertions
import java.util.*
import kotlin.test.assertNotNull

class StoreroomStepdefs(private val testContext: TestContext,
                        private val storeroomFactory: StoreroomFactory) {
    @Given("^a storeroom owner user$")
    fun `a storeroom owner user`() {
        testContext.userId = "storeroom owner user"
    }

    @And("^he has a storeroom$")
    fun `he has a storeroom`() {
        `he creates the storeroom`("Test Storeroom")
    }

    @And("^his storeroom has (\\d+) (.+)$")
    fun `his storeroom has some stock of the product`(stock: Int, productName: String) {
        testContext.productId = UUID.randomUUID().toString()
        testContext.storeroom = testContext.storeroom.addProduct(testContext.productId, testContext.userId, stock)
    }

    @When("^he creates the storeroom (.+)$")
    fun `he creates the storeroom`(storeroomName : String) {
        val storeroomId = UUID.randomUUID().toString()

        testContext.storeroom = storeroomFactory.create(storeroomId, testContext.userId, storeroomName)
    }

    @When("^he replenishes (\\d+) (.+) to his storeroom$")
    fun `he adds n quantity of the product to his storeroom`(quantity: Int, productName: String) {
        `his storeroom has some stock of the product`(quantity, productName)
    }

    @Then("^the storeroom is created$")
    fun `the storeroom is created`() {
        val storeroom = testContext.storeroom
        val adminUser = testContext.userId

        assertNotNull(storeroom, "Storeroom should be created")
        storeroom.that `corresponds to owner` adminUser
        storeroom.that `has name` storeroom.name
    }

    @Then("^he has (\\d+) (.+) in his storeroom$")
    fun `he has n quantity of the product in his storeroom`(quantity: Int, productName: String) {
        val storeroom = testContext.storeroom

        Assertions.assertThat(storeroom.stockOf(testContext.productId)).isEqualTo(quantity)
    }
}