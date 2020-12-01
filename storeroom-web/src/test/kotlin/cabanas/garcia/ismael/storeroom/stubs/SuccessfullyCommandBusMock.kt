package cabanas.garcia.ismael.storeroom.stubs

import cabanas.garcia.ismael.storeroom.application.Command
import cabanas.garcia.ismael.storeroom.application.shared.CommandBus
import cabanas.garcia.ismael.storeroom.application.storeroom.consumeproduct.ConsumeProductCommand
import cabanas.garcia.ismael.storeroom.application.storeroom.replenishproduct.ReplenishProductCommand
import cabanas.garcia.ismael.storeroom.application.storeroom.createstoreroom.CreateStoreroomCommand
import org.assertj.core.api.Assertions.assertThat

class SuccessfullyCommandBusMock(): CommandBus {
    private lateinit var commandDispatched : Command

    override fun <C : Command> dispatch(command: C) {
        commandDispatched = command
    }

    fun verifyCommandWasDispatched(expected: CreateStoreroomCommand) {
        val actual = this.commandDispatched as CreateStoreroomCommand

        assertThat(actual.storeroomId).isEqualTo(expected.storeroomId)
        assertThat(actual.storeroomName).isEqualTo(expected.storeroomName)
        assertThat(actual.ownerId).isEqualTo(expected.ownerId)
    }

    fun verifyCommandWasDispatched(expected: ReplenishProductCommand) {
        val actual = this.commandDispatched as ReplenishProductCommand

        assertThat(actual.storeroomId).isEqualTo(expected.storeroomId)
        assertThat(actual.productId).isEqualTo(expected.productId)
        assertThat(actual.userId).isEqualTo(expected.userId)
        assertThat(actual.quantity).isEqualTo(expected.quantity)
    }

    fun verifyCommandWasDispatched(expected: ConsumeProductCommand) {
        val actual = this.commandDispatched as ConsumeProductCommand

        assertThat(actual.storeroomId).isEqualTo(expected.storeroomId)
        assertThat(actual.productId).isEqualTo(expected.productId)
        assertThat(actual.userId).isEqualTo(expected.userId)
        assertThat(actual.quantity).isEqualTo(expected.quantity)
    }
}