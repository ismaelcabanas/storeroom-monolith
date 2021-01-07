package cabanas.garcia.ismael.storeroom.infrastructure.framework.eventhandler.shoppinglist.v1

import cabanas.garcia.ismael.storeroom.application.shared.bus.command.Command
import cabanas.garcia.ismael.storeroom.application.shared.bus.command.CommandBus
import cabanas.garcia.ismael.storeroom.application.shoppinglist.addproduct.AddProductCommand
import cabanas.garcia.ismael.storeroom.infrastructure.shared.bus.spring.event.ProductSoldOutSpringEvent
import org.springframework.context.ApplicationListener

class ProductSoldOutEventHandler(private val commandBus: CommandBus): ApplicationListener<ProductSoldOutSpringEvent> {
    override fun onApplicationEvent(event: ProductSoldOutSpringEvent) {
        commandBus.dispatch(toCommand(event))
    }

    private fun toCommand(event: ProductSoldOutSpringEvent): Command =
        AddProductCommand(
                event.storeroomId,
                event.productId,
                event.userId
        )
}