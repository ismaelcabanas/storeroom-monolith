package cabanas.garcia.ismael.storeroom.application.storeroom.addproduct

import cabanas.garcia.ismael.storeroom.application.CommandHandler
import cabanas.garcia.ismael.storeroom.domain.storeroom.StoreroomRepository

class AddProductCommandHandler(private val storeroomRepository: StoreroomRepository): CommandHandler<AddProductCommand> {
    override fun handle(command: AddProductCommand) {
        TODO("Not yet implemented")
    }

}
