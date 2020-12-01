package cabanas.garcia.ismael.storeroom.application.storeroom.replenishproduct

import cabanas.garcia.ismael.storeroom.application.CommandHandler
import cabanas.garcia.ismael.storeroom.domain.shared.eventbus.EventBus
import cabanas.garcia.ismael.storeroom.domain.storeroom.exception.StoreroomDoesNotExistException
import cabanas.garcia.ismael.storeroom.domain.storeroom.StoreroomRepository

class ReplenishProductCommandHandler(
        private val storeroomRepository: StoreroomRepository,
        private val eventBus: EventBus): CommandHandler<ReplenishProductCommand> {

    override fun handle(command: ReplenishProductCommand) {
        val storeroom = storeroomRepository.findById(command.storeroomId)
                ?: throw StoreroomDoesNotExistException(command.storeroomId)

        val storeroomUpdated = storeroom.addProduct(command.productId, command.userId, command.quantity)

        storeroomRepository.save(storeroomUpdated)

        eventBus.publish(storeroomUpdated.events())
    }

}