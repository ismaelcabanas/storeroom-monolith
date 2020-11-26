package cabanas.garcia.ismael.storeroom.application

/**
 * A handler for a [Command].
 *
 * @param <C> type of command
</C> */
interface CommandHandler<C : Command> {
    /**
     * Handles the command.
     *
     * @param command command to handle
     */
    fun handle(command: C)
}