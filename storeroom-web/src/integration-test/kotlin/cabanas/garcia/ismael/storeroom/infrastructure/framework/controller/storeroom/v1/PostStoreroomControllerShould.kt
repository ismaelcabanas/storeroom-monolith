package cabanas.garcia.ismael.storeroom.infrastructure.framework.controller.storeroom.v1

import cabanas.garcia.ismael.storeroom.application.storeroom.createstoreroom.CreateStoreroomCommand
import cabanas.garcia.ismael.storeroom.stubs.SuccessfullyCommandBusMock
import io.restassured.module.mockmvc.RestAssuredMockMvc
import io.restassured.module.mockmvc.RestAssuredMockMvc.given
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@ExtendWith(SpringExtension::class)
@WebMvcTest(value = [PostStoreroomController::class])
@Import(value = [CustomTestConfiguration::class])
@ActiveProfiles("integration-test")
@AutoConfigureMockMvc
class PostStoreroomControllerShould {

    @Autowired
    private lateinit var mvc: MockMvc

    @Autowired
    private lateinit var commandBus: SuccessfullyCommandBusMock

    @BeforeEach
    fun setUp() {
        RestAssuredMockMvc.mockMvc(mvc)
    }

    @Test
    fun `create a given storeroom`() {
        given()
                .contentType("application/json")
                .header("User-Id", SOME_STOREROOM_USER_ID)
                .body("""{
                          "id": "$SOME_STOREROOM_REQUEST_ID",
                          "name": "$SOME_STOREROOM_REQUEST_NAME"         
                        }"""
                )
                .`when`()
                .post("/v1/storerooms")
                .then()
                .assertThat(status().isCreated)
                .header("Location", equalTo("http://localhost/v1/storerooms/some%2520storeroom%2520request%2520id"))

        commandBus.verifyCommandWasDispatched(CreateStoreroomCommand(SOME_STOREROOM_REQUEST_ID, SOME_STOREROOM_USER_ID, SOME_STOREROOM_REQUEST_NAME))
    }

    companion object {
        private const val SOME_STOREROOM_REQUEST_ID = "some storeroom request id"
        private const val SOME_STOREROOM_REQUEST_NAME = "some storeroom request name"

        private const val SOME_STOREROOM_USER_ID = "some user id"
    }
}

@TestConfiguration
class CustomTestConfiguration {
    @Bean
    fun commandBus() = SuccessfullyCommandBusMock()
}