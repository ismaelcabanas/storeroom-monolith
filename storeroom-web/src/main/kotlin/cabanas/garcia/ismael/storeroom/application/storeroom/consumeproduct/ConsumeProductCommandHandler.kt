package cabanas.garcia.ismael.storeroom.application.storeroom.consumeproduct

import cabanas.garcia.ismael.storeroom.application.shared.bus.command.CommandHandler
import cabanas.garcia.ismael.storeroom.domain.shared.eventbus.EventBus
import cabanas.garcia.ismael.storeroom.domain.storeroom.StoreroomId
import cabanas.garcia.ismael.storeroom.domain.storeroom.exception.StoreroomDoesNotExistException
import cabanas.garcia.ismael.storeroom.domain.storeroom.StoreroomRepository
import mu.KotlinLogging

private val logger = KotlinLogging.logger {}

class ConsumeProductCommandHandler(
        private val storeroomRepository: StoreroomRepository,
        private val eventBus: EventBus): CommandHandler<ConsumeProductCommand> {

    override fun handle(command: ConsumeProductCommand) {
        storeroomRepository.findBy(StoreroomId(command.storeroomId))?.let { storeroom ->
            val storeroomUpdated = storeroom.consumeProduct(command.productId, command.userId, command.quantity)
            storeroomRepository.save(storeroomUpdated)
            eventBus.publish(storeroomUpdated.events())
            logger.info("Consume ${command.quantity} units of product '${command.productId}' from "
                    + "storeroom '${storeroomUpdated.name}' by user '{${command.userId}}'")
        } ?: throw StoreroomDoesNotExistException(StoreroomId(command.storeroomId))
    }

}
