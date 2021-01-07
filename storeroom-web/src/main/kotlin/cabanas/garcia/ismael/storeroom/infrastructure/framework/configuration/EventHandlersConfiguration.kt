package cabanas.garcia.ismael.storeroom.infrastructure.framework.configuration

import cabanas.garcia.ismael.storeroom.application.shared.bus.command.CommandBus
import cabanas.garcia.ismael.storeroom.infrastructure.framework.eventhandler.shoppinglist.v1.ProductSoldOutEventHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class EventHandlersConfiguration {
    @Bean
    fun productSoldOutEventHandler(commandBus: CommandBus) = ProductSoldOutEventHandler(commandBus)
}