package cabanas.garcia.ismael.storeroom.application.storeroom.consumeproduct

import cabanas.garcia.ismael.storeroom.application.shared.bus.command.Command

data class ConsumeProductCommand(val storeroomId: String,
                            val productId: String,
                            val quantity: Int,
                            val userId: String): Command {

    override fun toString(): String {
        return "ConsumeProductCommand(storeroomId='$storeroomId', productId='$productId', units=$quantity, userId='$userId')"
    }

}