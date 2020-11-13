package cabanas.garcia.ismael.storeroom.domain.productcatalog

class UserId(id: String) {
    val id: String = id

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as UserId

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    override fun toString(): String {
        return "UserId(id='$id')"
    }


}