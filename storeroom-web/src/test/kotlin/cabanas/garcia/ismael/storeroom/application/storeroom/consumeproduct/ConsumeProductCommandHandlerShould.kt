package cabanas.garcia.ismael.storeroom.application.storeroom.consumeproduct

import cabanas.garcia.ismael.storeroom.domain.shared.eventbus.InMemoryEventBus
import cabanas.garcia.ismael.storeroom.domain.storeroom.*
import cabanas.garcia.ismael.storeroom.domain.storeroom.event.ProductConsumed
import cabanas.garcia.ismael.storeroom.domain.storeroom.event.ProductSoldOut
import cabanas.garcia.ismael.storeroom.domain.storeroom.exception.StoreroomDoesNotExistException
import cabanas.garcia.ismael.storeroom.infrastructure.database.InMemoryDatabase
import cabanas.garcia.ismael.storeroom.infrastructure.framework.repository.storeroom.InMemoryStoreroomRepository
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

private const val SOME_STOREROOM_ID = "some storeroom id"
private const val SOME_PRODUCT_ID = "some product id"
private const val SOME_USER_ID = "some owner id"
private const val SOME_QUANTITY = 5

class ConsumeProductCommandHandlerShould {
    private val storeroomRepository = InMemoryStoreroomRepository()
    private lateinit var sut: ConsumeProductCommandHandler
    private lateinit var eventBus: InMemoryEventBus

    @BeforeEach
    fun `setUp`() {
        storeroomRepository.clean()
        eventBus = InMemoryEventBus()
        sut = ConsumeProductCommandHandler(storeroomRepository, eventBus)
    }

    @Test
    fun `save products in repository successfully`() {
        givenThatAlreadyExistAStoreroomWithProduct()

        sut.handle(command = ConsumeProductCommand(SOME_STOREROOM_ID, SOME_PRODUCT_ID, SOME_QUANTITY, SOME_USER_ID))

        assertThatProductWasPersistedInStoreroom()
    }

    @Test
    fun `throws exception when storeroom does not exist in repository`() {

        val throwable = Assertions.catchThrowable { sut.handle(command = ConsumeProductCommand(SOME_STOREROOM_ID, SOME_PRODUCT_ID, SOME_QUANTITY, SOME_USER_ID)) }

        assertThat(throwable).isInstanceOf(StoreroomDoesNotExistException::class.java)
    }

    @Test
    fun `publish product consumed event successfully`() {
        givenThatAlreadyExistAStoreroomWithProduct()

        sut.handle(command = ConsumeProductCommand(SOME_STOREROOM_ID, SOME_PRODUCT_ID, SOME_QUANTITY, SOME_USER_ID))

        assertThatProductAddedEventWasPublished()
    }

    @Test
    fun `publish product sold out event successfully`() {
        givenThatAlreadyExistAStoreroomWithProduct()

        sut.handle(command = ConsumeProductCommand(SOME_STOREROOM_ID, SOME_PRODUCT_ID, SOME_QUANTITY, SOME_USER_ID))

        assertThatProductSoldOutEventWasPublished()
    }

    private fun assertThatProductSoldOutEventWasPublished() {
        assertThat(eventBus.eventsPublished).contains(ProductSoldOut(SOME_PRODUCT_ID, SOME_STOREROOM_ID, SOME_USER_ID))
    }

    private fun assertThatProductAddedEventWasPublished() {
        assertThat(eventBus.eventsPublished).contains(ProductConsumed(SOME_PRODUCT_ID, SOME_STOREROOM_ID, SOME_USER_ID, SOME_QUANTITY))
    }

    private fun givenThatAlreadyExistAStoreroomWithProduct() {
        InMemoryDatabase.storerooms[StoreroomId(SOME_STOREROOM_ID)] = Storeroom(SOME_STOREROOM_ID, SOME_USER_ID, "Test Storeroom")
        InMemoryDatabase.products[ProductId(SOME_PRODUCT_ID)] = Product(SOME_PRODUCT_ID, SOME_QUANTITY)
    }

    private fun assertThatProductWasPersistedInStoreroom() {
        val product = InMemoryDatabase.products[ProductId(SOME_PRODUCT_ID)]

        assertThat(product).isNotNull
        assertThat(product!!.stock.value).isEqualTo(0)
    }
}