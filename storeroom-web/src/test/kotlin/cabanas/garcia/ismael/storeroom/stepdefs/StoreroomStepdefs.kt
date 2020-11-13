package cabanas.garcia.ismael.storeroom.stepdefs

import cabanas.garcia.ismael.storeroom.application.storeroom.createstoreroom.CreateStoreroomCommand
import cabanas.garcia.ismael.storeroom.application.storeroom.createstoreroom.CreateStoreroomCommandHandler
import cabanas.garcia.ismael.storeroom.assertions.that
import cabanas.garcia.ismael.storeroom.domain.storeroom.StoreroomRepository
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import java.util.*
import kotlin.test.assertNotNull

class StoreroomStepdefs(private val testContext: TestContext,
                        private val repository: StoreroomRepository,
                        private val createStoreroom: CreateStoreroomCommandHandler) {
    @Given("^a storeroom owner user$")
    fun `a storeroom owner user`() {
        testContext.userId = "storeroom owner user"
    }

    @When("^he creates the storeroom (.+)$")
    fun `he creates the storeroom`(storeroomName : String) {
        val storeroomId = UUID.randomUUID().toString()

        testContext.createStoreroomCommand = CreateStoreroomCommand(storeroomId, testContext.userId, storeroomName)

        createStoreroom.handle(testContext.createStoreroomCommand)
    }

    @Then("^the storeroom is created$")
    fun `the storeroom is created`() {
        val storeroom = repository.findById(testContext.createStoreroomCommand.storeroomId)
        val adminUser = testContext.userId

        assertNotNull(storeroom, "Storeroom should be created")
        storeroom.that `corresponds to owner` adminUser
        storeroom.that `has name` storeroom.name
    }

}