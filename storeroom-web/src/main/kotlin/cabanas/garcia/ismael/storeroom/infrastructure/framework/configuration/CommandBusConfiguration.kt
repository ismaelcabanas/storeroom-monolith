package cabanas.garcia.ismael.storeroom.infrastructure.framework.configuration

import cabanas.garcia.ismael.storeroom.application.Command
import cabanas.garcia.ismael.storeroom.application.CommandHandler
import cabanas.garcia.ismael.storeroom.application.shared.CommandBus
import cabanas.garcia.ismael.storeroom.application.shared.InMemoryCommandBus
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CommandBusConfiguration {
    @Bean
    fun inMemoryCommandBus(commandHandlers: List<CommandHandler<Command>>): CommandBus = InMemoryCommandBus()
}