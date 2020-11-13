package cabanas.garcia.ismael.storeroom.application.productcatalog.createproduct

import cabanas.garcia.ismael.storeroom.application.Command

class CreateProductCommand(productId: String, creatorId: String, productName: String): Command {
    val productId: String = productId
    val creatorId: String = creatorId
    val productName: String = productName

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CreateProductCommand

        if (productId != other.productId) return false
        if (creatorId != other.creatorId) return false
        if (productName != other.productName) return false

        return true
    }

    override fun hashCode(): Int {
        var result = productId.hashCode()
        result = 31 * result + creatorId.hashCode()
        result = 31 * result + productName.hashCode()
        return result
    }

    override fun toString(): String {
        return "CreateProductCommand(productId='$productId', creatorId='$creatorId', productName='$productName')"
    }


}