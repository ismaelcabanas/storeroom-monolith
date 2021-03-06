package cabanas.garcia.ismael.storeroom.infrastructure.framework.configuration

import cabanas.garcia.ismael.storeroom.application.shared.bus.command.Command
import cabanas.garcia.ismael.storeroom.application.shared.bus.command.CommandHandler
import cabanas.garcia.ismael.storeroom.application.shared.bus.command.CommandBus
import cabanas.garcia.ismael.storeroom.application.shoppinglist.addproduct.AddProductCommand
import cabanas.garcia.ismael.storeroom.application.shoppinglist.addproduct.AddProductCommandHandler
import cabanas.garcia.ismael.storeroom.infrastructure.shared.bus.InMemoryCommandBus
import cabanas.garcia.ismael.storeroom.application.storeroom.consumeproduct.ConsumeProductCommand
import cabanas.garcia.ismael.storeroom.application.storeroom.consumeproduct.ConsumeProductCommandHandler
import cabanas.garcia.ismael.storeroom.application.storeroom.createstoreroom.CreateStoreroomCommand
import cabanas.garcia.ismael.storeroom.application.storeroom.createstoreroom.CreateStoreroomCommandHandler
import cabanas.garcia.ismael.storeroom.application.storeroom.replenishproduct.ReplenishProductCommand
import cabanas.garcia.ismael.storeroom.application.storeroom.replenishproduct.ReplenishProductCommandHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CommandBusConfiguration {
    @Bean
    fun inMemoryCommandBus(
            createStoreroomCommandHandler: CreateStoreroomCommandHandler,
            replenishProductCommandHandler: ReplenishProductCommandHandler,
            consumeProductCommandHandler: ConsumeProductCommandHandler,
            addProductCommandHandler: AddProductCommandHandler): CommandBus {
        InMemoryCommandBus.registry[CreateStoreroomCommand::class.java.simpleName] = createStoreroomCommandHandler as CommandHandler<Command>
        InMemoryCommandBus.registry[ReplenishProductCommand::class.java.simpleName] = replenishProductCommandHandler as CommandHandler<Command>
        InMemoryCommandBus.registry[ConsumeProductCommand::class.java.simpleName] = consumeProductCommandHandler as CommandHandler<Command>
        InMemoryCommandBus.registry[AddProductCommand::class.java.simpleName] = addProductCommandHandler as CommandHandler<Command>
        return InMemoryCommandBus
    }
}