package cabanas.garcia.ismael.storeroom.infrastructure.shared.bus

import cabanas.garcia.ismael.storeroom.application.Command
import cabanas.garcia.ismael.storeroom.application.CommandHandler
import cabanas.garcia.ismael.storeroom.application.shared.CommandBus
import java.util.concurrent.ConcurrentHashMap

object InMemoryCommandBus: CommandBus {

    var registry: MutableMap<String, CommandHandler<Command>> = ConcurrentHashMap()

    override fun <C : Command> dispatch(command: C) {
        val commandHandler = registry[command.javaClass.simpleName] as CommandHandler<C>
        commandHandler.handle(command)
    }
}