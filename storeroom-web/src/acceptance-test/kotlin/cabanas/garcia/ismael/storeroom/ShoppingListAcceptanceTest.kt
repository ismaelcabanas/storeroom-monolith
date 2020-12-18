package cabanas.garcia.ismael.storeroom

import cabanas.garcia.ismael.storeroom.domain.shoppinglist.*
import cabanas.garcia.ismael.storeroom.infrastructure.database.InMemoryDatabase
import io.restassured.module.mockmvc.RestAssuredMockMvc
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.Test
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

class ShoppingListAcceptanceTest : AcceptanceTest() {
    @Test
    fun `get shopping list`() {
        givenShoppingListWithProducts()

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
                .body("products[0].bought", equalTo(false))
    }

    private fun givenShoppingListWithProducts() {
        InMemoryDatabase.shoppingLists[ShoppingListId(SOME_SHOPPING_LIST_ID)] = ShoppingList(ShoppingListId(SOME_SHOPPING_LIST_ID), StoreroomId(SOME_STOREROOM_ID), UserId(SOME_USER_ID))
        InMemoryDatabase.productsShoppingList[ProductId(SOME_PRODUCT_ID)] = Product(ProductId(SOME_PRODUCT_ID), SOME_PRODUCT_NAME, false)
    }

    companion object {
        private const val SOME_STOREROOM_ID = "some storeroom id"
        private const val SOME_USER_ID = "some user id"
        private const val SOME_SHOPPING_LIST_ID = "some shopping list id"

        private const val SOME_PRODUCT_ID = "some product id"
        private const val SOME_PRODUCT_NAME = "some product name"
    }
}