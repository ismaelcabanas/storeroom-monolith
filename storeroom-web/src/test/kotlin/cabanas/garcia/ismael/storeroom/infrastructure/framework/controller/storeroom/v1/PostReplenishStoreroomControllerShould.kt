package cabanas.garcia.ismael.storeroom.infrastructure.framework.controller.storeroom.v1

import cabanas.garcia.ismael.storeroom.application.storeroom.replenishproduct.ReplenishProductCommand
import cabanas.garcia.ismael.storeroom.stubs.SuccessfullyCommandBusMock
import io.restassured.module.mockmvc.RestAssuredMockMvc
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

class PostReplenishStoreroomControllerShould {
    private lateinit var commandBus: SuccessfullyCommandBusMock

    @BeforeEach
    fun configureSystemUnderTest() {
        commandBus = SuccessfullyCommandBusMock()

        RestAssuredMockMvc.standaloneSetup(PostReplenishStoreroomController(commandBus))
    }

    @Test
    fun `return 200 when post replenish product to a given storeroom`() {
        RestAssuredMockMvc.given()
                .contentType(JSON_CONTENT_TYPE)
                .header(USER_HEADER, SOME_STOREROOM_USER_ID)
                .body(validReplenishStoreroomRequestBody())
                .`when`()
                .post(POST_STOREROOM_REPLENISH_PATH)
                .then()
                .assertThat(MockMvcResultMatchers.status().isOk)
    }

    @Test
    fun `dispatch command when post replenish product to a given storeroom`() {
        RestAssuredMockMvc.given()
                .contentType(JSON_CONTENT_TYPE)
                .header(USER_HEADER, SOME_STOREROOM_USER_ID)
                .body(validReplenishStoreroomRequestBody())
                .`when`()
                .post(POST_STOREROOM_REPLENISH_PATH)

        commandBus.verifyCommandWasDispatched(ReplenishProductCommand(SOME_STOREROOM_ID, SOME_PRODUCT_REQUEST_ID, SOME_PRODUCT_REQUEST_QUANTITY, SOME_STOREROOM_USER_ID))
    }

    private fun validReplenishStoreroomRequestBody() = """{
                          "productId": "$SOME_PRODUCT_REQUEST_ID",
                          "quantity": "$SOME_PRODUCT_REQUEST_QUANTITY"         
                        }"""

    companion object {
        private const val SOME_STOREROOM_ID = "some storeroom id"
        private const val SOME_PRODUCT_REQUEST_ID = "some product request id"
        private const val SOME_PRODUCT_REQUEST_QUANTITY = 5

        private const val SOME_STOREROOM_USER_ID = "some user id"

        private const val JSON_CONTENT_TYPE = "application/json"
        private const val USER_HEADER = "User-Id"
        private const val POST_STOREROOM_REPLENISH_PATH = "/v1/storerooms/$SOME_STOREROOM_ID/replenish"

    }
}