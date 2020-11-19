package cabanas.garcia.ismael.storeroom.application.storeroom.createstoreroom

import cabanas.garcia.ismael.storeroom.application.CommandHandler
import cabanas.garcia.ismael.storeroom.domain.shared.eventbus.EventBus
import cabanas.garcia.ismael.storeroom.domain.storeroom.StoreroomCreated
import cabanas.garcia.ismael.storeroom.domain.storeroom.StoreroomFactory
import cabanas.garcia.ismael.storeroom.domain.storeroom.StoreroomRepository

class CreateStoreroomCommandHandler(
        private val factory: StoreroomFactory,
        private val repository: StoreroomRepository,
        private val eventBus: EventBus): CommandHandler<CreateStoreroomCommand> {

    override fun handle(command: CreateStoreroomCommand) {
        repository.save(factory.create(command.storeroomId, command.ownerId, command.storeroomName))
        eventBus.publish(StoreroomCreated(command.storeroomId, command.ownerId, command.storeroomName))
    }
}