package cabanas.garcia.ismael.storeroom.domain.shoppinglist

class ShoppingListId(value: String) {
    val value: String = value

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ShoppingListId

        if (value != other.value) return false

        return true
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }

    override fun toString(): String {
        return "ShoppingListId(value='$value')"
    }


}