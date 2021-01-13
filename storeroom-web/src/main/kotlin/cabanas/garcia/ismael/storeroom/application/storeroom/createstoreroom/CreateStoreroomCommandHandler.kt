package cabanas.garcia.ismael.storeroom.application.storeroom.createstoreroom

import cabanas.garcia.ismael.storeroom.application.shared.bus.command.CommandHandler
import cabanas.garcia.ismael.storeroom.domain.shared.eventbus.EventBus
import cabanas.garcia.ismael.storeroom.domain.storeroom.event.StoreroomCreated
import cabanas.garcia.ismael.storeroom.domain.storeroom.StoreroomFactory
import cabanas.garcia.ismael.storeroom.domain.storeroom.StoreroomRepository
import mu.KotlinLogging

private val logger = KotlinLogging.logger {}

class CreateStoreroomCommandHandler(
        private val factory: StoreroomFactory,
        private val repository: StoreroomRepository,
        private val eventBus: EventBus): CommandHandler<CreateStoreroomCommand> {

    override fun handle(command: CreateStoreroomCommand) {
        repository.save(factory.create(command.storeroomId, command.ownerId, command.storeroomName))
        eventBus.publish(StoreroomCreated(command.storeroomId, command.ownerId, command.storeroomName))
        logger.info("Storeroom '${command.storeroomName}' created by '${command.ownerId}'")
    }
}