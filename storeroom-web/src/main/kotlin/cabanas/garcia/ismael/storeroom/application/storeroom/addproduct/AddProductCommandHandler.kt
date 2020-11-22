package cabanas.garcia.ismael.storeroom.application.storeroom.addproduct

import cabanas.garcia.ismael.storeroom.application.CommandHandler
import cabanas.garcia.ismael.storeroom.domain.shared.eventbus.EventBus
import cabanas.garcia.ismael.storeroom.domain.storeroom.ProductAdded
import cabanas.garcia.ismael.storeroom.domain.storeroom.StoreroomDoesNotExistException
import cabanas.garcia.ismael.storeroom.domain.storeroom.StoreroomRepository

class AddProductCommandHandler(
        private val storeroomRepository: StoreroomRepository,
        private val eventBus: EventBus): CommandHandler<AddProductCommand> {

    override fun handle(command: AddProductCommand) {
        val storeroom = storeroomRepository.findById(command.storeroomId)
                ?: throw StoreroomDoesNotExistException(command.storeroomId)

        val storeroomUpdated = storeroom.addProduct(command.productId, command.userId, command.quantity)

        storeroomRepository.save(storeroomUpdated)

        eventBus.publish(ProductAdded(command.productId, command.storeroomId, command.userId, command.quantity))
    }

}
