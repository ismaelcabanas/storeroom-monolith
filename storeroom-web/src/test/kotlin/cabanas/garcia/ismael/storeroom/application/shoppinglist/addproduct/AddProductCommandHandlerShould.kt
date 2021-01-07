package cabanas.garcia.ismael.storeroom.application.shoppinglist.addproduct

import cabanas.garcia.ismael.storeroom.domain.productcatalog.Product as ProductCatalog
import cabanas.garcia.ismael.storeroom.domain.productcatalog.UserId as ProductCatalogUserId
import cabanas.garcia.ismael.storeroom.domain.productcatalog.ProductRepository
import cabanas.garcia.ismael.storeroom.domain.productcatalog.ProductId as ProductCatalogId
import cabanas.garcia.ismael.storeroom.domain.productcatalog.spi.stubs.InMemoryProductRepository
import cabanas.garcia.ismael.storeroom.domain.shoppinglist.*
import cabanas.garcia.ismael.storeroom.domain.shoppinglist.exception.FindByStoreroomShoppingListDoesNotExist
import cabanas.garcia.ismael.storeroom.infrastructure.database.InMemoryDatabase
import cabanas.garcia.ismael.storeroom.infrastructure.framework.repository.shoppinglist.InMemoryShoppingListRepository
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.catchThrowable
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class AddProductCommandHandlerShould {

    private val shoppingListRepository: ShoppingListRepository = InMemoryShoppingListRepository()
    private lateinit var sut: AddProductCommandHandler
    private val productRepository: ProductRepository = InMemoryProductRepository()

    @BeforeEach
    fun `setUp`() {
        InMemoryDatabase.clean()
        sut = AddProductCommandHandler(shoppingListRepository, productRepository)
    }

    @Test
    fun `save products in repository successfully`() {
        givenThatAlreadyExistAShoppingList()
        givenThatAlreadyExistProductsInCatalog()

        sut.handle(command = AddProductCommand(SOME_STOREROOM_ID, SOME_PRODUCT_ID, SOME_USER_ID))

        assertThatProductWasPersistedInShoppingList()
    }

    @Test
    fun `throws exception when shopping list does not exist in repository`() {

        val throwable = catchThrowable { sut.handle(command = AddProductCommand(SOME_STOREROOM_ID, SOME_PRODUCT_ID, SOME_USER_ID)) }

        assertThat(throwable).isInstanceOf(FindByStoreroomShoppingListDoesNotExist::class.java)
    }

    @Test
    fun `throws exception when product does not exist`() {
        givenThatAlreadyExistAShoppingList()

        val throwable = catchThrowable { sut.handle(command = AddProductCommand(SOME_STOREROOM_ID, SOME_PRODUCT_ID, SOME_USER_ID)) }

        assertThat(throwable).isInstanceOf(ProductDoesNotExitsException::class.java)
    }

    private fun givenThatAlreadyExistAShoppingList() {
        InMemoryDatabase.shoppingLists[ShoppingListId(SOME_SHOPPING_LIST_ID)] = ShoppingList(ShoppingListId(SOME_SHOPPING_LIST_ID), StoreroomId(SOME_STOREROOM_ID), UserId(SOME_USER_ID))
    }

    private fun givenThatAlreadyExistProductsInCatalog() {
        InMemoryDatabase.productsCatalog[ProductCatalogId(SOME_PRODUCT_ID)] = ProductCatalog(ProductCatalogId(SOME_PRODUCT_ID), ProductCatalogUserId(SOME_USER_ID), SOME_PRODUCT_NAME)
    }

    private fun assertThatProductWasPersistedInShoppingList() {
        val product = InMemoryDatabase.productsShoppingList[ProductId(SOME_PRODUCT_ID)]

        assertThat(product).isNotNull
    }

    companion object {
        const val SOME_SHOPPING_LIST_ID = "some shopping list id"
        const val SOME_STOREROOM_ID = "some storeroom id"
        const val SOME_PRODUCT_ID = "some product id"
        const val SOME_PRODUCT_NAME = "some product name"
        const val SOME_USER_ID = "some owner id"
    }
}