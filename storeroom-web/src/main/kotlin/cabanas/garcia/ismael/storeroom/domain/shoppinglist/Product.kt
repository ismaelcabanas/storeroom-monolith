package cabanas.garcia.ismael.storeroom.domain.shoppinglist

class Product(val id: ProductId, val name: String, val bought: Boolean = false) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Product

        if (id != other.id) return false
        if (name != other.name) return false
        if (bought != other.bought) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + bought.hashCode()
        return result
    }

    override fun toString(): String {
        return "Product(id=$id, name='$name', bought=$bought)"
    }


}