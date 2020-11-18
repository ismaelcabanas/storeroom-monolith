package cabanas.garcia.ismael.storeroom.domain.shoppinglist

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.catchThrowable
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

private const val SOME_STOREROOM_ID: String = "some id"
private const val SOME_OWNER_ID: String = "some user id"
private const val SOME_SHOPPING_LIST_ID: String = "some shopping list id"
private const val SOME_PRODUCT_ID: String = "some product id"
private const val SOME_STOCK: Int = 5

class ShoppingListShould {
    @BeforeEach
    fun `setUp`() {

    }

    @Test
    fun `create a shopping list for a storeroom`() {
        val sut = ShoppingList.create(SOME_SHOPPING_LIST_ID, SOME_STOREROOM_ID, SOME_OWNER_ID)

        assertThat(sut).isNotNull
        assertThat(sut.id.value).isEqualTo(SOME_SHOPPING_LIST_ID)
        assertThat(sut.storeroomId.value).isEqualTo(SOME_STOREROOM_ID)
        assertThat(sut.ownerId.value).isEqualTo(SOME_OWNER_ID)
    }

    @Test
    fun `add new product to shopping list`() {
        val sut = ShoppingList.create(SOME_SHOPPING_LIST_ID, SOME_STOREROOM_ID, SOME_OWNER_ID)

        val actual = sut.addProduct(SOME_PRODUCT_ID)

        assertThat(actual.productBought(SOME_PRODUCT_ID)).isFalse()
    }

    @Test
    fun `throws product does not exists error when adds a existent product in shopping list`() {
        val sut = ShoppingListMother.createShoppingListWithProducts(listOf(Product(ProductId(SOME_PRODUCT_ID))))

        val throwable = catchThrowable { sut.addProduct(SOME_PRODUCT_ID) }

        assertThat(throwable).isInstanceOf(ProductAlreadyExitsException::class.java)
        assertThat(throwable.message).isEqualTo("Product '$SOME_PRODUCT_ID' already exist in the shopping list")
    }

}