package cabanas.garcia.ismael.storeroom.application.productcatalog.create

import cabanas.garcia.ismael.storeroom.application.storeroom.createstoreroom.CreateStoreroomCommand
import cabanas.garcia.ismael.storeroom.application.storeroom.createstoreroom.CreateStoreroomCommandHandler
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

    @BeforeEach
    fun `setUp`() {
        storeroomRepository = InMemoryStoreroomRepository()
    }

    @Test
    fun `save a storeroom successfully`() {
        val sut = CreateStoreroomCommandHandler(DefaultStoreroomFactory(), storeroomRepository)

        sut.handle(command = CreateStoreroomCommand(SOME_STOREROOM_ID, SOME_OWNER_ID, SOME_STOREROOM_NAME))

        assertThatStoreroomWasPersisted(SOME_STOREROOM_ID, SOME_OWNER_ID, SOME_STOREROOM_NAME)
    }

    private fun assertThatStoreroomWasPersisted(productId: String, ownerId: String, productName: String) {
        val storeroom = storeroomRepository.findById(productId)

        assertThat(storeroom).isNotNull
        assertThat(storeroom!!.name).isEqualTo(productName)
        assertThat(storeroom!!.ownerId).isEqualTo(UserId(ownerId))
    }
}