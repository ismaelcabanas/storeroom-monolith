package cabanas.garcia.ismael.storeroom.application.shoppinglist.addproduct

import cabanas.garcia.ismael.storeroom.application.Command

data class AddProductCommand(
        val shoppingListId: String,
        val productId: String,
        val userId: String): Command