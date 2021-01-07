package cabanas.garcia.ismael.storeroom.application.shoppinglist.addproduct

import cabanas.garcia.ismael.storeroom.application.shared.bus.command.Command

data class AddProductCommand(
        val storeroomId: String,
        val productId: String,
        val userId: String): Command