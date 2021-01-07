package cabanas.garcia.ismael.storeroom.infrastructure.framework.eventhandler.shoppinglist.v1

import cabanas.garcia.ismael.storeroom.IntegrationTest
import cabanas.garcia.ismael.storeroom.application.shared.bus.command.Command
import cabanas.garcia.ismael.storeroom.application.shared.bus.command.CommandBus
import cabanas.garcia.ismael.storeroom.application.shoppinglist.addproduct.AddProductCommand
import cabanas.garcia.ismael.storeroom.domain.shared.eventbus.EventBus
import cabanas.garcia.ismael.storeroom.domain.storeroom.event.ProductSoldOut
import com.ninjasquad.springmockk.MockkBean
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier

class ProductSoldOutEventHandlerShould: IntegrationTest() {
    @Autowired
    @Qualifier("springEventBus")
    private lateinit var eventBus: EventBus

    @MockkBean
    private lateinit var commandBus: CommandBus

    @Test
    fun `process product sold out event`() {
        every { commandBus.dispatch(ofType(Command::class)) } just Runs
        val event = ProductSoldOut(
                SOME_PRODUCT_ID,
                SOME_STOREROOM_ID,
                SOME_USER_ID
        )
        eventBus.publish(event)

        verify {
            commandBus.dispatch(
                    AddProductCommand(
                            SOME_STOREROOM_ID,
                            SOME_PRODUCT_ID,
                            SOME_USER_ID
                    )
            )
        }
    }

    companion object {
        private const val SOME_PRODUCT_ID = "4471245f-85aa-4e84-93a4-fb77d6ef693d"
        private const val SOME_STOREROOM_ID = "f6201709-d195-41d2-87fd-5478be2cccf5"
        private const val SOME_USER_ID = "b9a45269-1588-41cf-a0c4-228a9c5a9b50"
    }
}