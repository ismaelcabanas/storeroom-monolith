package cabanas.garcia.ismael.storeroom.domain.storeroom

class Storeroom(id: StoreroomId, ownerId: UserId, name: String) {
    val id: StoreroomId = id
    val ownerId: UserId = ownerId
    val name: String = name

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Storeroom

        if (id != other.id) return false
        if (ownerId != other.ownerId) return false
        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + ownerId.hashCode()
        result = 31 * result + name.hashCode()
        return result
    }

    override fun toString(): String {
        return "Storeroom(id='$id', ownerId='$ownerId', name='$name')"
    }


}
