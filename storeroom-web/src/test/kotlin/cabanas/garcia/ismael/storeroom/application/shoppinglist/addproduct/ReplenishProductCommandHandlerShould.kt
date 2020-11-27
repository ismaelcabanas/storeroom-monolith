package cabanas.garcia.ismael.storeroom.application.shoppinglist.addproduct

import cabanas.garcia.ismael.storeroom.domain.shoppinglist.*
import cabanas.garcia.ismael.storeroom.domain.storeroom.spi.InMemoryDatabase
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.catchThrowable
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ReplenishProductCommandHandlerShould {

    private lateinit var database: InMemoryDatabase
    private lateinit var shoppingListRepository: ShoppingListRepository
    private lateinit var sut: AddProductCommandHandler

    @BeforeEach
    fun `setUp`() {
        database = InMemoryDatabase()
        shoppingListRepository = InMemoryShoppingRepository(database)
        sut = AddProductCommandHandler(shoppingListRepository)
    }

    @Test
    fun `save products in repository successfully`() {
        givenThatAlreadyExistAShoppingList()

        sut.handle(command = AddProductCommand(SOME_SHOPPING_LIST_ID, SOME_PRODUCT_ID, SOME_USER_ID))

        assertThatProductWasPersistedInShoppingList()
    }

    @Test
    fun `throws exception when shopping list does not exist in repository`() {

        val throwable = catchThrowable { sut.handle(command = AddProductCommand(SOME_SHOPPING_LIST_ID, SOME_PRODUCT_ID, SOME_USER_ID)) }

        assertThat(throwable).isInstanceOf(ShoppingListDoesNotExistException::class.java)
    }

    private fun givenThatAlreadyExistAShoppingList() {
        database.shoppingLists[ShoppingListId(SOME_SHOPPING_LIST_ID)] = ShoppingList(ShoppingListId(SOME_SHOPPING_LIST_ID), StoreroomId(SOME_STOREROOM_ID), UserId(SOME_USER_ID))
    }

    private fun assertThatProductWasPersistedInShoppingList() {
        val product = database.productsShoppingList[ProductId(SOME_PRODUCT_ID)]

        assertThat(product).isNotNull
    }

    companion object {
        const val SOME_SHOPPING_LIST_ID = "some shopping list id"
        const val SOME_STOREROOM_ID = "some storeroom id"
        const val SOME_PRODUCT_ID = "some product id"
        const val SOME_USER_ID = "some owner id"
    }
}