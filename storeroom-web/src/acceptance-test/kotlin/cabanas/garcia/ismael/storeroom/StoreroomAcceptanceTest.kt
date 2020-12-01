package cabanas.garcia.ismael.storeroom

import cabanas.garcia.ismael.storeroom.domain.storeroom.*
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

        assertThatStoreroomWasCreatedWithoutProducts()
    }

    @Test
    fun `replenish products to storeroom successfully`() {
        val currentStock = 4
        givenStoreroomHasProductWithStock(currentStock)

        RestAssuredMockMvc.given()
                .contentType("application/json")
                .header("User-Id", SOME_STOREROOM_USER_ID)
                .body("""{
                          "productId": "$SOME_PRODUCT_REQUEST_ID",
                          "quantity": "$SOME_PRODUCT_REQUEST_QUANTITY"         
                        }"""
                )
                .`when`()
                .post("/v1/storerooms/$SOME_STOREROOM_REQUEST_ID/replenish")

        assertThatProductWasReplenishedInStoreroomWithStock(currentStock + SOME_PRODUCT_REQUEST_QUANTITY)
    }

    private fun givenStoreroomHasProductWithStock(currentStock: Int) {
        InMemoryDatabase.storerooms[StoreroomId(SOME_STOREROOM_REQUEST_ID)] = Storeroom(StoreroomId(SOME_STOREROOM_REQUEST_ID), UserId(SOME_STOREROOM_USER_ID), SOME_STOREROOM_REQUEST_NAME)
        InMemoryDatabase.products[ProductId(SOME_PRODUCT_REQUEST_ID)] = Product(ProductId(SOME_PRODUCT_REQUEST_ID), Stock(currentStock))
    }

    private fun assertThatProductWasReplenishedInStoreroomWithStock(expectedStock: Int) {
        val storeroomProduct = InMemoryDatabase.products[ProductId(SOME_PRODUCT_REQUEST_ID)]
        assertThat(storeroomProduct!!).isNotNull
        assertThat(storeroomProduct.stock.value).isEqualTo(expectedStock)
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

        private const val SOME_PRODUCT_REQUEST_ID = "some product request id"
        private const val SOME_PRODUCT_REQUEST_QUANTITY = 5

        private const val SOME_STOREROOM_USER_ID = "some user id"
    }
}