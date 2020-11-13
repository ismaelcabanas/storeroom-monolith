package cabanas.garcia.ismael.storeroom.application.storeroom.createstoreroom

import cabanas.garcia.ismael.storeroom.application.CommandHandler
import cabanas.garcia.ismael.storeroom.domain.storeroom.StoreroomFactory
import cabanas.garcia.ismael.storeroom.domain.storeroom.StoreroomRepository

class CreateStoreroomCommandHandler(private val factory: StoreroomFactory,
                                    private val repository: StoreroomRepository): CommandHandler<CreateStoreroomCommand> {

    override fun handle(command: CreateStoreroomCommand) {
        val storeroom = factory.create(command.storeroomId, command.ownerId, command.storeroomName)
        repository.save(storeroom)
    }
}