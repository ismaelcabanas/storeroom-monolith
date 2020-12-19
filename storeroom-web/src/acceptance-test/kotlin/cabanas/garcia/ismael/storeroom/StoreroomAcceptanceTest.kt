package cabanas.garcia.ismael.storeroom

import cabanas.garcia.ismael.storeroom.domain.storeroom.ProductMother
import cabanas.garcia.ismael.storeroom.domain.storeroom.Storeroom
import cabanas.garcia.ismael.storeroom.domain.storeroom.StoreroomMother
import io.restassured.module.mockmvc.RestAssuredMockMvc
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.test.jdbc.JdbcTestUtils
import java.util.UUID

class StoreroomAcceptanceTest : AcceptanceTest() {

    @Test
    fun `create storeroom successfully`() {
        val storeroom = StoreroomMother.emptyStoreroom()

        createStoreroom(storeroom)

        assertThatStoreroomWasCreatedWithoutProducts(storeroom)
    }

    @Test
    fun `replenish products to storeroom successfully`() {
        val product = ProductMother.aProductWithStock(4)
        val storeroom = StoreroomMother.aStoreroomWithProducts()
        createStoreroom(storeroom)

        replenishProductWithQuantity(storeroom, product.id.value, product.stock())

        assertThatProductInStoreroomHasStock(storeroom.id.value, product.id.value, 4)
    }

    @Test
    fun `consume products from storeroom successfully`() {
        val product = ProductMother.aProductWithStock(9)
        val storeroom = StoreroomMother.aStoreroomWithProducts()
        createStoreroom(storeroom)
        replenishProductWithQuantity(storeroom, product.id.value, product.stock())

        consumeQuantityProduct(storeroom, product.id.value, 5)

        assertThatProductInStoreroomHasStock(storeroom.id.value, product.id.value,4)
    }

    private fun consumeQuantityProduct(storeroom: Storeroom, productId: String, quantity: Int) {
        RestAssuredMockMvc.given()
                .contentType("application/json")
                .header("User-Id", storeroom.ownerId.value)
                .body("""{
                          "productId": "$productId",
                          "quantity": "$quantity"         
                        }"""
                )
                .`when`()
                .post("/v1/storerooms/${storeroom.id.value}/consume")
    }

    private fun createStoreroom(storeroom: Storeroom) {
        RestAssuredMockMvc.given()
                .contentType("application/json")
                .header("User-Id", storeroom.ownerId.value)
                .body("""{
                          "id": "${storeroom.id.value}",
                          "name": "${storeroom.name}"         
                        }"""
                )
                .`when`()
                .post("/v1/storerooms")
    }

    private fun replenishProductWithQuantity(storeroom: Storeroom, productId: String, quantity: Int) {
        RestAssuredMockMvc.given()
                .contentType("application/json")
                .header("User-Id", storeroom.ownerId.value)
                .body("""{
                          "productId": "$productId",
                          "quantity": "$quantity"         
                        }"""
                )
                .`when`()
                .post("/v1/storerooms/${storeroom.id.value}/replenish")
    }

    private fun assertThatProductInStoreroomHasStock(expectedStoreroomId: String, expectedProductId: String, expectedStock: Int) {
        assertThat(
                JdbcTestUtils.countRowsInTableWhere(
                        jdbcTemplate,
                        "STOREROOM_PRODUCT",
                        "ID = '$expectedProductId'"
                                + " AND STOREROOM_ID = '$expectedStoreroomId'"
                                + " AND STOCK = $expectedStock"
                )
        ).isEqualTo(1)
    }

    private fun assertThatStoreroomWasCreatedWithoutProducts(expected: Storeroom) {
        assertThat(
                JdbcTestUtils.countRowsInTableWhere(
                        jdbcTemplate,
                        "STOREROOM",
                        "ID = '" + expected.id.value + "'"
                                + " AND OWNER_ID = '" + expected.ownerId.value + "'"
                                + " AND NAME = '" + expected.name + "'"
                )
        ).isEqualTo(1)
    }

    companion object {
        private val SOME_STOREROOM_REQUEST_ID = UUID.randomUUID().toString()
        private const val SOME_STOREROOM_REQUEST_NAME = "some storeroom request name"

        private val SOME_PRODUCT_REQUEST_ID = UUID.randomUUID().toString()

        private val SOME_STOREROOM_USER_ID = UUID.randomUUID().toString()
    }
}