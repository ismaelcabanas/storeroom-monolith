package cabanas.garcia.ismael.storeroom.domain.storeroom

class UserId(value: String) {
    val value: String = value

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as UserId

        if (value != other.value) return false

        return true
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }

    override fun toString(): String {
        return "UserId(id='$value')"
    }


}