package cabanas.garcia.ismael.storeroom.application.shared.bus.command

interface CommandBus {
    fun <C : Command> dispatch(command:C)
}