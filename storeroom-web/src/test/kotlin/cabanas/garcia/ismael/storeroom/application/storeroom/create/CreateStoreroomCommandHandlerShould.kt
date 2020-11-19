package cabanas.garcia.ismael.storeroom.application.storeroom.create

import cabanas.garcia.ismael.storeroom.application.storeroom.createstoreroom.CreateStoreroomCommand
import cabanas.garcia.ismael.storeroom.application.storeroom.createstoreroom.CreateStoreroomCommandHandler
import cabanas.garcia.ismael.storeroom.domain.shared.eventbus.InMemoryEventBus
import cabanas.garcia.ismael.storeroom.domain.storeroom.StoreroomCreated
import cabanas.garcia.ismael.storeroom.domain.storeroom.UserId
import cabanas.garcia.ismael.storeroom.domain.storeroom.spi.DefaultStoreroomFactory
import cabanas.garcia.ismael.storeroom.domain.storeroom.spi.InMemoryStoreroomRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

private const val SOME_STOREROOM_ID = "some storeroom id"
private const val SOME_OWNER_ID = "some owner id"
private const val SOME_STOREROOM_NAME = "some storeroom name"

class CreateStoreroomCommandHandlerShould {

    private lateinit var storeroomRepository: InMemoryStoreroomRepository
    private lateinit var eventBus: InMemoryEventBus

    @BeforeEach
    fun `setUp`() {
        storeroomRepository = InMemoryStoreroomRepository()
        eventBus = InMemoryEventBus()
    }

    @Test
    fun `save a storeroom successfully`() {
        val sut = CreateStoreroomCommandHandler(DefaultStoreroomFactory(), storeroomRepository, eventBus)

        sut.handle(command = CreateStoreroomCommand(SOME_STOREROOM_ID, SOME_OWNER_ID, SOME_STOREROOM_NAME))

        assertThatStoreroomWasPersisted()
    }

    @Test
    fun `publish storeroom created event successfully`() {
        val sut = CreateStoreroomCommandHandler(DefaultStoreroomFactory(), storeroomRepository, eventBus)

        sut.handle(command = CreateStoreroomCommand(SOME_STOREROOM_ID, SOME_OWNER_ID, SOME_STOREROOM_NAME))

        assertThatStoreroomCreatedEventWasPublished()
    }

    private fun assertThatStoreroomCreatedEventWasPublished() {
        val event = eventBus.get(SOME_STOREROOM_ID) as StoreroomCreated?

        assertThat(event).isNotNull
        assertThat(event).isInstanceOf(StoreroomCreated::class.java)
        assertThat(event!!.storeroomId).isEqualTo(SOME_STOREROOM_ID)
        assertThat(event!!.storeroomName).isEqualTo(SOME_STOREROOM_NAME)
        assertThat(event!!.storeroomOwnerId).isEqualTo(SOME_OWNER_ID)
    }

    private fun assertThatStoreroomWasPersisted() {
        val storeroom = storeroomRepository.findById(SOME_STOREROOM_ID)

        assertThat(storeroom).isNotNull
        assertThat(storeroom!!.name).isEqualTo(SOME_STOREROOM_NAME)
        assertThat(storeroom!!.ownerId).isEqualTo(UserId(SOME_OWNER_ID))
    }
}