package cabanas.garcia.ismael.storeroom.infrastructure.framework.configuration

import cabanas.garcia.ismael.storeroom.application.Command
import cabanas.garcia.ismael.storeroom.application.CommandHandler
import cabanas.garcia.ismael.storeroom.application.shared.CommandBus
import cabanas.garcia.ismael.storeroom.application.shared.InMemoryCommandBus
import cabanas.garcia.ismael.storeroom.application.storeroom.createstoreroom.CreateStoreroomCommand
import cabanas.garcia.ismael.storeroom.application.storeroom.createstoreroom.CreateStoreroomCommandHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CommandBusConfiguration {
    @Bean
    fun inMemoryCommandBus(createStoreroomCommandHandler : CreateStoreroomCommandHandler): CommandBus {
        InMemoryCommandBus.registry[CreateStoreroomCommand::class.java.simpleName] = createStoreroomCommandHandler as CommandHandler<Command>

        return InMemoryCommandBus
    }
}