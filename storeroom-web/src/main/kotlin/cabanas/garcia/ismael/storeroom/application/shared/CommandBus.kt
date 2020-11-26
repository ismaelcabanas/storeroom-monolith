package cabanas.garcia.ismael.storeroom.application.shared

import cabanas.garcia.ismael.storeroom.application.Command

interface CommandBus {
    fun <C : Command> dispatch(command:C)
}