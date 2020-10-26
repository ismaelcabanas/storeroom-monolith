package cabanas.garcia.ismael.storeroom.domain.product

class Product(id: ProductId, userId: UserId, name: String) {
    val id: ProductId = id
    val userId: UserId = userId
    val name: String = name

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Product

        if (id != other.id) return false
        if (userId != other.userId) return false
        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + userId.hashCode()
        result = 31 * result + name.hashCode()
        return result
    }

    override fun toString(): String {
        return "Product(id=$id, userId=$userId, name='$name')"
    }




}