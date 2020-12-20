package cabanas.garcia.ismael.storeroom.application.shoppinglist.addproduct

import cabanas.garcia.ismael.storeroom.application.shared.bus.command.CommandHandler
import cabanas.garcia.ismael.storeroom.domain.shoppinglist.ShoppingListId
import cabanas.garcia.ismael.storeroom.domain.shoppinglist.ShoppingListRepository

class AddProductCommandHandler(
       private val shoppingListRepository: ShoppingListRepository): CommandHandler<AddProductCommand> {

    override fun handle(command: AddProductCommand) {
        shoppingListRepository.findBy(ShoppingListId( command.shoppingListId))?.let { shoppingList ->
            val shoppingListUpdated = shoppingList.addProduct(command.productId, command.productName)
            shoppingListRepository.save(shoppingListUpdated)
        }
    }
}
