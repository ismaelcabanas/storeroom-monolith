package cabanas.garcia.ismael.storeroom.domain.product

class Product(id: ProductId, creatorId: UserId, name: String) {
    val id: ProductId = id
    val creatorId: UserId = creatorId
    val name: String = name

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Product

        if (id != other.id) return false
        if (creatorId != other.creatorId) return false
        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + creatorId.hashCode()
        result = 31 * result + name.hashCode()
        return result
    }

    override fun toString(): String {
        return "Product(id=$id, creatorId=$creatorId, name='$name')"
    }




}