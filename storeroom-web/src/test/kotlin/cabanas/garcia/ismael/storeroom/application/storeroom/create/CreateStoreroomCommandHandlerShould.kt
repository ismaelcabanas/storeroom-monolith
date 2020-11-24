package cabanas.garcia.ismael.storeroom.application.storeroom.create

import cabanas.garcia.ismael.storeroom.application.storeroom.createstoreroom.CreateStoreroomCommand
import cabanas.garcia.ismael.storeroom.application.storeroom.createstoreroom.CreateStoreroomCommandHandler
import cabanas.garcia.ismael.storeroom.domain.shared.eventbus.InMemoryEventBus
import cabanas.garcia.ismael.storeroom.domain.storeroom.event.StoreroomCreated
import cabanas.garcia.ismael.storeroom.domain.storeroom.StoreroomId
import cabanas.garcia.ismael.storeroom.domain.storeroom.StoreroomRepository
import cabanas.garcia.ismael.storeroom.domain.storeroom.UserId
import cabanas.garcia.ismael.storeroom.domain.storeroom.spi.DefaultStoreroomFactory
import cabanas.garcia.ismael.storeroom.domain.storeroom.spi.InMemoryDatabase
import cabanas.garcia.ismael.storeroom.domain.storeroom.spi.InMemoryStoreroomRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

private const val SOME_STOREROOM_ID = "some storeroom id"
private const val SOME_OWNER_ID = "some owner id"
private const val SOME_STOREROOM_NAME = "some storeroom name"

class CreateStoreroomCommandHandlerShould {

    private lateinit var database: InMemoryDatabase
    private lateinit var storeroomRepository: StoreroomRepository
    private lateinit var eventBus: InMemoryEventBus

    @BeforeEach
    fun `setUp`() {
        database = InMemoryDatabase()
        storeroomRepository = InMemoryStoreroomRepository(database)
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
        assertThat(eventBus.eventsPublished).contains(StoreroomCreated(SOME_STOREROOM_ID, SOME_OWNER_ID, SOME_STOREROOM_NAME))
    }

    private fun assertThatStoreroomWasPersisted() {
        val storeroom = database.storerooms[StoreroomId(SOME_STOREROOM_ID)]

        assertThat(storeroom).isNotNull
        assertThat(storeroom!!.name).isEqualTo(SOME_STOREROOM_NAME)
        assertThat(storeroom!!.ownerId).isEqualTo(UserId(SOME_OWNER_ID))
    }
}