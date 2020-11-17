package cabanas.garcia.ismael.storeroom.domain.shoppinglist

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

private const val SOME_STOREROOM_ID: String = "some id"
private const val SOME_OWNER_ID:String = "some user id"
private const val SOME_SHOPPING_LIST_ID:String = "some shopping list id"

class ShoppingListShould {
    @BeforeEach
    fun `setUp`() {

    }

    @Test
    fun `create a shopping list for a storeroom`() {
        val sut = ShoppingList.create(SOME_SHOPPING_LIST_ID, SOME_STOREROOM_ID, SOME_OWNER_ID)

        Assertions.assertThat(sut).isNotNull
        Assertions.assertThat(sut.id.value).isEqualTo(SOME_SHOPPING_LIST_ID)
        Assertions.assertThat(sut.storeroomId.value).isEqualTo(SOME_STOREROOM_ID)
        Assertions.assertThat(sut.ownerId.value).isEqualTo(SOME_OWNER_ID)
    }

}