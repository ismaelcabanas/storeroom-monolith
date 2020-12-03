package cabanas.garcia.ismael.storeroom.application.storeroom.replenishproduct

import cabanas.garcia.ismael.storeroom.domain.shared.eventbus.InMemoryEventBus
import cabanas.garcia.ismael.storeroom.domain.storeroom.*
import cabanas.garcia.ismael.storeroom.domain.storeroom.event.ProductAdded
import cabanas.garcia.ismael.storeroom.domain.storeroom.exception.StoreroomDoesNotExistException
import cabanas.garcia.ismael.storeroom.infrastructure.database.InMemoryDatabase
import cabanas.garcia.ismael.storeroom.infrastructure.framework.repository.storeroom.InMemoryStoreroomRepository
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.catchThrowable
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

private const val SOME_STOREROOM_ID = "some storeroom id"
private const val SOME_PRODUCT_ID = "some product id"
private const val SOME_USER_ID = "some owner id"
private const val SOME_QUANTITY = 5

class ReplenishProductCommandHandlerShould {

    private val storeroomRepository = InMemoryStoreroomRepository()
    private lateinit var sut: ReplenishProductCommandHandler
    private lateinit var eventBus: InMemoryEventBus

    @BeforeEach
    fun `setUp`() {
        storeroomRepository.clean()
        eventBus = InMemoryEventBus()
        sut = ReplenishProductCommandHandler(storeroomRepository, eventBus)
    }

    @Test
    fun `save products in repository successfully`() {
        givenThatAlreadyExistAStoreroom()

        sut.handle(command = ReplenishProductCommand(SOME_STOREROOM_ID, SOME_PRODUCT_ID, SOME_QUANTITY, SOME_USER_ID))

        assertThatProductWasPersistedInStoreroom()
    }

    @Test
    fun `throws exception when storeroom does not exist in repository`() {

        val throwable = catchThrowable { sut.handle(command = ReplenishProductCommand(SOME_STOREROOM_ID, SOME_PRODUCT_ID, SOME_QUANTITY, SOME_USER_ID)) }

        assertThat(throwable).isInstanceOf(StoreroomDoesNotExistException::class.java)
    }

    @Test
    fun `publish product added event successfully`() {
        givenThatAlreadyExistAStoreroom()

        sut.handle(command = ReplenishProductCommand(SOME_STOREROOM_ID, SOME_PRODUCT_ID, SOME_QUANTITY, SOME_USER_ID))

        assertThatProductAddedEventWasPublished()
    }

    private fun assertThatProductAddedEventWasPublished() {
        assertThat(eventBus.eventsPublished).contains(ProductAdded(SOME_PRODUCT_ID, SOME_STOREROOM_ID, SOME_USER_ID, SOME_QUANTITY))
    }

    private fun givenThatAlreadyExistAStoreroom() {
        InMemoryDatabase.storerooms[StoreroomId(SOME_STOREROOM_ID)] = Storeroom(StoreroomId(SOME_STOREROOM_ID), UserId(SOME_USER_ID), "Test Storeroom")
    }

    private fun assertThatProductWasPersistedInStoreroom() {
        val product = InMemoryDatabase.products[ProductId(SOME_PRODUCT_ID)]

        assertThat(product).isNotNull
        assertThat(product!!.stock.value).isEqualTo(SOME_QUANTITY)
    }
}