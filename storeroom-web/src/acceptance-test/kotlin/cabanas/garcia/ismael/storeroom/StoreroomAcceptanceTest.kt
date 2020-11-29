package cabanas.garcia.ismael.storeroom

import cabanas.garcia.ismael.storeroom.domain.storeroom.StoreroomId
import cabanas.garcia.ismael.storeroom.infrastructure.database.InMemoryDatabase
import cabanas.garcia.ismael.storeroom.infrastructure.framework.StoreroomWebApplication
import io.restassured.module.mockmvc.RestAssuredMockMvc
import org.assertj.core.api.Assertions.assertThat
import org.hamcrest.Matchers
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@ExtendWith(SpringExtension::class)
@SpringBootTest(
        classes = [StoreroomWebApplication::class],
        properties = ["spring.profiles.active=acceptance-test"]
)
@AutoConfigureMockMvc
class StoreroomAcceptanceTest {
    @Autowired
    private lateinit var mvc: MockMvc

    @BeforeEach
    fun setUp() {
        InMemoryDatabase.clean()
        RestAssuredMockMvc.mockMvc(mvc)
    }

    @Test
    fun `create storeroom successfully`() {
        RestAssuredMockMvc.given()
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
                .assertThat(MockMvcResultMatchers.status().isCreated)
                .header("Location", Matchers.equalTo("http://localhost/v1/storerooms/some%2520storeroom%2520request%2520id"))

        assertThatStoreroomWasCreatedWithoutProducts()
    }

    private fun assertThatStoreroomWasCreatedWithoutProducts() {
        val storeroom = InMemoryDatabase.storerooms[StoreroomId(SOME_STOREROOM_REQUEST_ID)]
        assertThat(storeroom!!).isNotNull
        assertThat(storeroom.name).isEqualTo(SOME_STOREROOM_REQUEST_NAME)
        assertThat(storeroom.ownerId.value).isEqualTo(SOME_STOREROOM_USER_ID)
        assertThat(storeroom.products()).isEmpty()
    }

    companion object {
        private const val SOME_STOREROOM_REQUEST_ID = "some storeroom request id"
        private const val SOME_STOREROOM_REQUEST_NAME = "some storeroom request name"

        private const val SOME_STOREROOM_USER_ID = "some user id"
    }
}