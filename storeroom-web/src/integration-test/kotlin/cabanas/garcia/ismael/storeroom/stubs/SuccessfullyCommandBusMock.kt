package cabanas.garcia.ismael.storeroom.stubs

import cabanas.garcia.ismael.storeroom.application.Command
import cabanas.garcia.ismael.storeroom.application.shared.CommandBus
import cabanas.garcia.ismael.storeroom.application.storeroom.createstoreroom.CreateStoreroomCommand
import org.assertj.core.api.Assertions.assertThat

class SuccessfullyCommandBusMock(): CommandBus {
    private lateinit var commandDispatched : Command

    override fun <C : Command> dispatch(command: C) {
        commandDispatched = command
    }

    fun verifyCommandWasDispatched(expected: CreateStoreroomCommand) {
        val actual = this.commandDispatched as CreateStoreroomCommand

        assertThat(expected.storeroomId).isEqualTo(actual.storeroomId)
        assertThat(expected.storeroomName).isEqualTo(actual.storeroomName)
        assertThat(expected.ownerId).isEqualTo(actual.ownerId)
    }
}