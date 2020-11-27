package cabanas.garcia.ismael.storeroom.infrastructure.framework.controller.storeroom.v1

import cabanas.garcia.ismael.storeroom.application.storeroom.replenishproduct.ReplenishProductCommand
import cabanas.garcia.ismael.storeroom.stubs.SuccessfullyCommandBusMock
import io.restassured.module.mockmvc.RestAssuredMockMvc
import io.restassured.module.mockmvc.RestAssuredMockMvc.given
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@ExtendWith(SpringExtension::class)
@WebMvcTest(value = [PostReplenishStoreroomController::class])
@Import(value = [CustomTestConfiguration::class])
@ActiveProfiles("integration-test")
@AutoConfigureMockMvc
class PostReplenishStoreroomControllerShould {

    @Autowired
    private lateinit var mvc: MockMvc

    @Autowired
    private lateinit var commandBus: SuccessfullyCommandBusMock

    @BeforeEach
    fun setUp() {
        RestAssuredMockMvc.mockMvc(mvc)
    }

    @Test
    fun `replenish products to a given storeroom`() {
        given()
                .contentType("application/json")
                .header("User-Id", SOME_STOREROOM_USER_ID)
                .body("""{
                          "productId": "$SOME_PRODUCT_REQUEST_ID",
                          "quantity": "$SOME_PRODUCT_REQUEST_QUANTITY"         
                        }"""
                )
                .`when`()
                .post("/v1/storerooms/$SOME_STOREROOM_ID/replenish")
                .then()
                .assertThat(status().isOk)

        commandBus.verifyCommandWasDispatched(ReplenishProductCommand(SOME_STOREROOM_ID, SOME_PRODUCT_REQUEST_ID, SOME_PRODUCT_REQUEST_QUANTITY, SOME_STOREROOM_USER_ID))
    }

    companion object {
        private const val SOME_STOREROOM_ID = "some storeroom id"
        private const val SOME_PRODUCT_REQUEST_ID = "some product request id"
        private const val SOME_PRODUCT_REQUEST_QUANTITY = 5

        private const val SOME_STOREROOM_USER_ID = "some user id"

    }
}