package cabanas.garcia.ismael.storeroom.infrastructure.framework.controller.storeroom.v1

import cabanas.garcia.ismael.storeroom.application.storeroom.createstoreroom.CreateStoreroomCommand
import cabanas.garcia.ismael.storeroom.stubs.SuccessfullyCommandBusMock
import io.restassured.module.mockmvc.RestAssuredMockMvc
import org.hamcrest.Matchers
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

class PostStoreroomControllerShould {

    private lateinit var commandBus: SuccessfullyCommandBusMock

    @BeforeEach
    fun configureSystemUnderTest() {
        commandBus = SuccessfullyCommandBusMock()

        RestAssuredMockMvc.standaloneSetup(PostStoreroomController(commandBus))
    }

    @Test
    fun `return 201 when post a valid storeroom`() {
        RestAssuredMockMvc.given()
                .contentType(JSON_CONTENT_TYPE)
                .header(USER_HEADER, SOME_STOREROOM_USER_ID)
                .body(validCreateStoreroomRequestBody())
                .`when`()
                .post(POST_STOREROOM_PATH)
                .then()
                .assertThat(MockMvcResultMatchers.status().isCreated)
    }

    @Test
    fun `return location header when post a valid storeroom`() {
        RestAssuredMockMvc.given()
                .contentType(JSON_CONTENT_TYPE)
                .header(USER_HEADER, SOME_STOREROOM_USER_ID)
                .body(validCreateStoreroomRequestBody())
                .`when`()
                .post(POST_STOREROOM_PATH)
                .then()
                .header("Location", Matchers.equalTo("http://localhost/v1/storerooms/some%2520storeroom%2520request%2520id"))
    }

    @Test
    fun `dispatch command when post a valid storeroom`() {
        RestAssuredMockMvc.given()
                .contentType(JSON_CONTENT_TYPE)
                .header(USER_HEADER, SOME_STOREROOM_USER_ID)
                .body(validCreateStoreroomRequestBody())
                .`when`()
                .post(POST_STOREROOM_PATH)

        commandBus.verifyCommandWasDispatched(CreateStoreroomCommand(SOME_STOREROOM_REQUEST_ID, SOME_STOREROOM_USER_ID, SOME_STOREROOM_REQUEST_NAME))
    }

    private fun validCreateStoreroomRequestBody() = """{
                          "id": "$SOME_STOREROOM_REQUEST_ID",
                          "name": "$SOME_STOREROOM_REQUEST_NAME"         
                        }"""

    companion object {
        private const val SOME_STOREROOM_REQUEST_ID = "some storeroom request id"
        private const val SOME_STOREROOM_REQUEST_NAME = "some storeroom request name"

        private const val SOME_STOREROOM_USER_ID = "some user id"

        private const val JSON_CONTENT_TYPE = "application/json"
        private const val USER_HEADER = "User-Id"
        private const val POST_STOREROOM_PATH = "/v1/storerooms"
    }
}