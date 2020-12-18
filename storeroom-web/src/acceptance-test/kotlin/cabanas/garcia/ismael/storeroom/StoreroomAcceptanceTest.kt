package cabanas.garcia.ismael.storeroom

import cabanas.garcia.ismael.storeroom.domain.storeroom.*
import cabanas.garcia.ismael.storeroom.infrastructure.database.InMemoryDatabase
import cabanas.garcia.ismael.storeroom.infrastructure.framework.StoreroomWebApplication
import io.restassured.module.mockmvc.RestAssuredMockMvc
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.jdbc.JdbcTestUtils
import org.springframework.test.web.servlet.MockMvc
import java.util.*

class StoreroomAcceptanceTest : AcceptanceTest() {
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

        assertThatProductInStoreroomHasStock(currentStock + SOME_PRODUCT_REQUEST_QUANTITY)
    }

    @Test
    fun `consume products from storeroom successfully`() {
        val currentStock = 9
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
                .post("/v1/storerooms/$SOME_STOREROOM_REQUEST_ID/consume")

        assertThatProductInStoreroomHasStock(currentStock - SOME_PRODUCT_REQUEST_QUANTITY)
    }

    private fun givenStoreroomHasProductWithStock(currentStock: Int) {
        InMemoryDatabase.storerooms[StoreroomId(SOME_STOREROOM_REQUEST_ID)] = Storeroom(StoreroomId(SOME_STOREROOM_REQUEST_ID), UserId(SOME_STOREROOM_USER_ID), SOME_STOREROOM_REQUEST_NAME)
        InMemoryDatabase.products[ProductId(SOME_PRODUCT_REQUEST_ID)] = Product(ProductId(SOME_PRODUCT_REQUEST_ID), Stock(currentStock))
    }

    private fun assertThatProductInStoreroomHasStock(expectedStock: Int) {
        val storeroomProduct = InMemoryDatabase.products[ProductId(SOME_PRODUCT_REQUEST_ID)]
        assertThat(storeroomProduct!!).isNotNull
        assertThat(storeroomProduct.stock.value).isEqualTo(expectedStock)
    }

    private fun assertThatStoreroomWasCreatedWithoutProducts() {
        assertThat(
                JdbcTestUtils.countRowsInTableWhere(
                        jdbcTemplate,
                        "STOREROOM",
                        "ID = '" + SOME_STOREROOM_REQUEST_ID + "'"
                                + " AND OWNER_ID = '" + SOME_STOREROOM_USER_ID + "'"
                                + " AND NAME = '" + SOME_STOREROOM_REQUEST_NAME + "'"
                )
        ).isEqualTo(1)
    }

    companion object {
        private val SOME_STOREROOM_REQUEST_ID = UUID.randomUUID().toString()
        private const val SOME_STOREROOM_REQUEST_NAME = "some storeroom request name"

        private const val SOME_PRODUCT_REQUEST_ID = "some product request id"
        private const val SOME_PRODUCT_REQUEST_QUANTITY = 5

        private val SOME_STOREROOM_USER_ID = UUID.randomUUID().toString()
    }
}