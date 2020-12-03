package cabanas.garcia.ismael.shoppinglist

import cabanas.garcia.ismael.storeroom.domain.shoppinglist.*
import cabanas.garcia.ismael.storeroom.infrastructure.database.InMemoryDatabase
import cabanas.garcia.ismael.storeroom.infrastructure.framework.StoreroomWebApplication
import io.restassured.module.mockmvc.RestAssuredMockMvc
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.hamcrest.Matchers.equalTo

@ExtendWith(SpringExtension::class)
@SpringBootTest(
        classes = [StoreroomWebApplication::class],
        properties = ["spring.profiles.active=acceptance-test"]
)
@AutoConfigureMockMvc
class ShoppingListAcceptanceTest {
    @Autowired
    private lateinit var mvc: MockMvc

    @BeforeEach
    fun setUp() {
        InMemoryDatabase.clean()
        RestAssuredMockMvc.mockMvc(mvc)
    }

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
                .body("products[0].bought", equalTo(false))
    }

    private fun givenShoppingListWithProducts() {
        InMemoryDatabase.shoppingLists[ShoppingListId(SOME_SHOPPING_LIST_ID)] = ShoppingList(ShoppingListId(SOME_SHOPPING_LIST_ID), StoreroomId(SOME_STOREROOM_ID), UserId(SOME_USER_ID))
        InMemoryDatabase.productsShoppingList[ProductId(SOME_PRODUCT_ID)] = Product(ProductId(SOME_PRODUCT_ID), false)
    }

    companion object {
        private const val SOME_STOREROOM_ID = "some storeroom id"
        private const val SOME_USER_ID = "some user id"
        private const val SOME_SHOPPING_LIST_ID = "some shopping list id"

        private const val SOME_PRODUCT_ID = "some product id"
    }
}