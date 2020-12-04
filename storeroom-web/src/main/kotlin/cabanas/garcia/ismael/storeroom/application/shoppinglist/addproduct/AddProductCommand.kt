package cabanas.garcia.ismael.storeroom.application.shoppinglist.addproduct

import cabanas.garcia.ismael.storeroom.application.shared.bus.command.Command

data class AddProductCommand(
        val shoppingListId: String,
        val productId: String,
        val productName: String,
        val userId: String): Command