package cabanas.garcia.ismael.storeroom

import cabanas.garcia.ismael.storeroom.domain.storeroom.ProductMother
import cabanas.garcia.ismael.storeroom.domain.storeroom.Storeroom
import cabanas.garcia.ismael.storeroom.domain.storeroom.StoreroomMother
import io.restassured.module.mockmvc.RestAssuredMockMvc
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.test.jdbc.JdbcTestUtils

class StoreroomAcceptanceTest : AcceptanceTest() {

    @Test
    fun `create storeroom successfully`() {
        val storeroom = StoreroomMother.emptyStoreroom(storeroomName = "some storeroom name")

        createStoreroom(storeroom)

        assertThatStoreroomWasCreatedWithoutProducts(storeroom)
    }

    @Test
    fun `replenish products to storeroom successfully`() {
        val product = ProductMother.aProductWithStock(4)
        val storeroom = StoreroomMother.aStoreroomWithProducts(storeroomName = "some storeroom for replenish")
        createStoreroom(storeroom)

        replenishProductWithQuantity(storeroom, product.id.value, 4)

        assertThatProductInStoreroomHasStock(storeroom.id.value, product.id.value, 4)
    }

    @Test
    fun `consume products from storeroom successfully`() {
        val product = ProductMother.aProductWithStock(9)
        val storeroom = StoreroomMother.aStoreroomWithProducts(storeroomName = "some storeroom for consume")
        createStoreroom(storeroom)
        replenishProductWithQuantity(storeroom, product.id.value, 9)

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
}