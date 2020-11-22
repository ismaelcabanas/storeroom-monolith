package cabanas.garcia.ismael.storeroom.application.storeroom.consumeproduct

import cabanas.garcia.ismael.storeroom.application.CommandHandler
import cabanas.garcia.ismael.storeroom.domain.shared.eventbus.EventBus
import cabanas.garcia.ismael.storeroom.domain.storeroom.event.ProductConsumed
import cabanas.garcia.ismael.storeroom.domain.storeroom.event.ProductSoldOut
import cabanas.garcia.ismael.storeroom.domain.storeroom.exception.StoreroomDoesNotExistException
import cabanas.garcia.ismael.storeroom.domain.storeroom.StoreroomRepository

class ConsumeProductCommandHandler(
        private val storeroomRepository: StoreroomRepository,
        private val eventBus: EventBus): CommandHandler<ConsumeProductCommand> {

    override fun handle(command: ConsumeProductCommand) {
        val storeroom = storeroomRepository.findById(command.storeroomId)
                ?: throw StoreroomDoesNotExistException(command.storeroomId)

        val storeroomUpdated = storeroom.consumeProduct(command.productId, command.userId, command.quantity)

        storeroomRepository.save(storeroomUpdated)

        eventBus.publish(ProductConsumed(command.productId, command.storeroomId, command.userId, storeroomUpdated.stockOf(command.productId)))

        if (storeroomUpdated.stockOf(command.productId) == 0) {
            eventBus.publish(ProductSoldOut(command.productId, command.userId))
        }
    }

}
