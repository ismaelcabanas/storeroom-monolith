package cabanas.garcia.ismael.storeroom.domain.shoppinglist

class ProductId(id: String) {
    val value: String = id

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ProductId

        if (value != other.value) return false

        return true
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }

    override fun toString(): String {
        return "ProductId(value='$value')"
    }


}
