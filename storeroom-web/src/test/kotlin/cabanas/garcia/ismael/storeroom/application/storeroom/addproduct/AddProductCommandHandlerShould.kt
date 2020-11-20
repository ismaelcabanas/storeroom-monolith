package cabanas.garcia.ismael.storeroom.application.storeroom.addproduct

import cabanas.garcia.ismael.storeroom.domain.storeroom.*
import cabanas.garcia.ismael.storeroom.domain.storeroom.spi.InMemoryStoreroomDatabase
import cabanas.garcia.ismael.storeroom.domain.storeroom.spi.InMemoryStoreroomRepository
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.catchThrowable
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

private const val SOME_STOREROOM_ID = "some storeroom id"
private const val SOME_PRODUCT_ID = "some product id"
private const val SOME_USER_ID = "some owner id"
private const val SOME_QUANTITY = 5

class AddProductCommandHandlerShould {

    private lateinit var storeroomDatabase: InMemoryStoreroomDatabase
    private lateinit var storeroomRepository: StoreroomRepository
    private lateinit var sut: AddProductCommandHandler

    @BeforeEach
    fun `setUp`() {
        storeroomDatabase = InMemoryStoreroomDatabase()
        storeroomRepository = InMemoryStoreroomRepository(storeroomDatabase)
        sut = AddProductCommandHandler(storeroomRepository)
    }

    @Test
    fun `save products in storeroom successfully`() {
        givenThatAlreadyExistAStoreroom()

        sut.handle(command = AddProductCommand(SOME_STOREROOM_ID, SOME_PRODUCT_ID, SOME_QUANTITY, SOME_USER_ID))

        assertThatProductWasPersistedInStoreroom()
    }

    @Test
    fun `throws exception when save products in storeroom that does not exist`() {

        val throwable = catchThrowable { sut.handle(command = AddProductCommand(SOME_STOREROOM_ID, SOME_PRODUCT_ID, SOME_QUANTITY, SOME_USER_ID)) }

        assertThat(throwable).isInstanceOf(StoreroomDoesNotExistException::class.java)
    }

    private fun givenThatAlreadyExistAStoreroom() {
        storeroomDatabase.storerooms[StoreroomId(SOME_STOREROOM_ID)] = Storeroom(StoreroomId(SOME_STOREROOM_ID), UserId(SOME_USER_ID), "Test Storeroom")
    }

    private fun assertThatProductWasPersistedInStoreroom() {
        val product = storeroomDatabase.products[ProductId(SOME_PRODUCT_ID)]

        assertThat(product).isNotNull
        assertThat(product!!.stock.value).isEqualTo(SOME_QUANTITY)
    }
}