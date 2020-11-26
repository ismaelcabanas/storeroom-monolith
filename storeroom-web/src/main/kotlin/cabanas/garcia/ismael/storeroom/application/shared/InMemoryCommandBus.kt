package cabanas.garcia.ismael.storeroom.application.shared

import cabanas.garcia.ismael.storeroom.application.Command
import cabanas.garcia.ismael.storeroom.application.CommandHandler
import java.util.concurrent.ConcurrentHashMap

class InMemoryCommandBus(private val registry: MutableMap<String, CommandHandler<Command>> = ConcurrentHashMap()): CommandBus {

    fun register(commandHandler: CommandHandler<Command>) = registry.put(commandHandler.javaClass.simpleName, commandHandler)

    override fun <C : Command> dispatch(command: C) {
        val commandHandler = registry[command.javaClass.simpleName] as CommandHandler<C>
        commandHandler.handle(command)
    }
}