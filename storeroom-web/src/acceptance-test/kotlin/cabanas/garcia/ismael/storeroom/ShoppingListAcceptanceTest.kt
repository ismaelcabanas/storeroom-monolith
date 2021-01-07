package cabanas.garcia.ismael.storeroom

import cabanas.garcia.ismael.storeroom.domain.storeroom.event.ProductSoldOut
import io.restassured.module.mockmvc.RestAssuredMockMvc
import org.awaitility.Awaitility.await
import org.awaitility.Duration
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.Test
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.jdbc.JdbcTestUtils
import org.springframework.test.web.servlet.result.MockMvcResultMatchers


class ShoppingListAcceptanceTest : AcceptanceTest() {
    @Test
    @Sql(statements = [
        "DELETE FROM SHOPPING_LIST",
        "INSERT INTO SHOPPING_LIST VALUES ('$SOME_SHOPPING_LIST_ID', '$SOME_STOREROOM_ID', '$SOME_USER_ID')",
        "INSERT INTO SHOPPING_LIST_PRODUCTS VALUES('$SOME_PRODUCT_ID', '$SOME_SHOPPING_LIST_ID', '$SOME_PRODUCT_NAME')"
    ])
    fun `get shopping list`() {
        RestAssuredMockMvc.given()
                .contentType("application/json")
                .header("User-Id", SOME_USER_ID)
                .`when`()
                .get("/v1/shoppinglists/$SOME_SHOPPING_LIST_ID")
                .then()
                .log().all()
                .assertThat(MockMvcResultMatchers.status().isOk)
                .body("products[0].id", equalTo(SOME_PRODUCT_ID))
                .body("products[0].name", equalTo(SOME_PRODUCT_NAME))
    }

    @Test
    @Sql(statements = [
        "DELETE FROM PRODUCT", "DELETE FROM SHOPPING_LIST", "DELETE FROM SHOPPING_LIST_PRODUCTS",
        "INSERT INTO SHOPPING_LIST VALUES ('$SOME_SHOPPING_LIST_ID', '$SOME_STOREROOM_ID', '$SOME_USER_ID')",
        "INSERT INTO PRODUCT VALUES('$SOME_PRODUCT_ID', '$SOME_PRODUCT_NAME', '$SOME_USER_ID')"
    ])
    fun `add product to shopping list when the product is out of stock`() {
        givenProductSoldOutEventSent()

        await().atMost(Duration.ONE_SECOND)
                .until { productIsInShoppingList() }
    }

    private fun productIsInShoppingList(): Boolean =
        JdbcTestUtils.countRowsInTableWhere(
                jdbcTemplate,
                "SHOPPING_LIST_PRODUCTS",
                "ID = '$SOME_PRODUCT_ID'"
                            + " AND SHOPPING_LIST_ID= '$SOME_SHOPPING_LIST_ID'"
                            + " AND NAME = '$SOME_PRODUCT_NAME'"
        ) == 1

    private fun givenProductSoldOutEventSent() {
        eventBus.publish(ProductSoldOut(SOME_PRODUCT_ID, SOME_STOREROOM_ID, SOME_USER_ID))
    }

    companion object {
        private const val SOME_STOREROOM_ID = "5e459290-dbb4-431b-a928-d639ebe7c043"
        private const val SOME_SHOPPING_LIST_ID = "d4ef6f36-4d47-4456-b86b-058ef58251c7"

        private const val SOME_USER_ID = "028f5812-3a79-45a8-b534-f1b7540a9092"
        private const val SOME_PRODUCT_ID = "1f87d7a9-9968-4ccb-8810-cf86566f6467"
        private const val SOME_PRODUCT_NAME = "some product name"
    }
}