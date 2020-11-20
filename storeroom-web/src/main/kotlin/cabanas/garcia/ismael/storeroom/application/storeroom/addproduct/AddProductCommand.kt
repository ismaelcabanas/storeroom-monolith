package cabanas.garcia.ismael.storeroom.application.storeroom.addproduct

import cabanas.garcia.ismael.storeroom.application.Command

class AddProductCommand(val storeroomId: String,
                        val productId: String,
                        val quantity: Int,
                        val userId: String): Command {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AddProductCommand

        if (storeroomId != other.storeroomId) return false
        if (productId != other.productId) return false
        if (quantity != other.quantity) return false
        if (userId != other.userId) return false

        return true
    }

    override fun hashCode(): Int {
        var result = storeroomId.hashCode()
        result = 31 * result + productId.hashCode()
        result = 31 * result + quantity
        result = 31 * result + userId.hashCode()
        return result
    }

    override fun toString(): String {
        return "AddProductCommand(storeroomId='$storeroomId', productId='$productId', units=$quantity, userId='$userId')"
    }


}