package cabanas.garcia.ismael.storeroom.application.shoppinglist.addproduct

import cabanas.garcia.ismael.storeroom.application.shared.bus.command.CommandHandler
import cabanas.garcia.ismael.storeroom.domain.shoppinglist.ShoppingListDoesNotExistException
import cabanas.garcia.ismael.storeroom.domain.shoppinglist.ShoppingListRepository

class AddProductCommandHandler(
        private val shoppingListRepository: ShoppingListRepository): CommandHandler<AddProductCommand> {

    override fun handle(command: AddProductCommand) {
        val shoppingList = shoppingListRepository.findById(command.shoppingListId)
                ?: throw ShoppingListDoesNotExistException(command.shoppingListId)

        val shoppingListUpdated = shoppingList.addProduct(command.productId)

        shoppingListRepository.save(shoppingListUpdated)
    }

}
