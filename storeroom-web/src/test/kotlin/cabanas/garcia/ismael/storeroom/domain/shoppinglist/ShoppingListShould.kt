package cabanas.garcia.ismael.storeroom.domain.shoppinglist

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class ShoppingListShould {
    @Test
    fun `create a shopping list for a storeroom`() {
        val actualShoppingList = ShoppingList.create(SOME_SHOPPING_LIST_ID, SOME_STOREROOM_ID, SOME_OWNER_ID)

        val expectedShoppingList = ShoppingList(SOME_SHOPPING_LIST_ID, SOME_STOREROOM_ID, SOME_OWNER_ID)
        actualShoppingList shouldBe expectedShoppingList
    }

    @Test
    fun `add new product to shopping list`() {
        val shoppingList = ShoppingList.create(SOME_SHOPPING_LIST_ID, SOME_STOREROOM_ID, SOME_OWNER_ID)

        val actualShoppingList = shoppingList.addProduct(Product(ProductId(SOME_PRODUCT_ID), SOME_PRODUCT_NAME))

        val expectedShoppingList = ShoppingList(SOME_SHOPPING_LIST_ID, SOME_STOREROOM_ID, SOME_OWNER_ID, listOf(Product(ProductId(SOME_PRODUCT_ID), SOME_PRODUCT_NAME)))
        actualShoppingList shouldBe expectedShoppingList
    }

    @Test
    fun `throws product does not exists error when adds a existent product in shopping list`() {
        val someProduct = Product(ProductId(SOME_PRODUCT_ID), SOME_PRODUCT_NAME)
        val shoppingList = ShoppingListMother.createShoppingListWithProducts(listOf(someProduct))

        val exception = shouldThrow<ProductAlreadyExitsException> {
            shoppingList.addProduct(Product(ProductId(SOME_PRODUCT_ID), SOME_PRODUCT_NAME))
        }
        exception.message shouldBe "Product '$SOME_PRODUCT_ID' already exist in the shopping list"
    }

    companion object {
        private const val SOME_STOREROOM_ID = "some id"
        private const val SOME_OWNER_ID = "some user id"
        private const val SOME_SHOPPING_LIST_ID = "some shopping list id"
        private const val SOME_PRODUCT_ID = "some product id"
        private const val SOME_PRODUCT_NAME = "some product name"
    }
}