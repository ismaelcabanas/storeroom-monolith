package cabanas.garcia.ismael.storeroom.domain.product

class ProductDetails(id: ProductId, name: String) {
    val id: ProductId = id
    val name: String = name

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ProductDetails

        if (id != other.id) return false
        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + name.hashCode()
        return result
    }

    override fun toString(): String {
        return "ProductDetails(id=$id, name='$name')"
    }


}