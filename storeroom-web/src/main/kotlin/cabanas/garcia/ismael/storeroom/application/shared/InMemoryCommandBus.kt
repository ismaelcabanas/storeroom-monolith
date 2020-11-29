package cabanas.garcia.ismael.storeroom.application.shared

import cabanas.garcia.ismael.storeroom.application.Command
import cabanas.garcia.ismael.storeroom.application.CommandHandler
import java.util.concurrent.ConcurrentHashMap

object InMemoryCommandBus: CommandBus {

    var registry: MutableMap<String, CommandHandler<Command>> = ConcurrentHashMap()

    override fun <C : Command> dispatch(command: C) {
        val commandHandler = registry[command.javaClass.simpleName] as CommandHandler<C>
        commandHandler.handle(command)
    }
}