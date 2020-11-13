package cabanas.garcia.ismael.storeroom.application.storeroom.createstoreroom

import cabanas.garcia.ismael.storeroom.application.Command

class CreateStoreroomCommand(storeroomId: String, ownerId: String, storeroomName: String): Command {
    val storeroomId: String = storeroomId
    val ownerId: String = ownerId
    val storeroomName: String = storeroomName

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CreateStoreroomCommand

        if (storeroomId != other.storeroomId) return false
        if (ownerId != other.ownerId) return false
        if (storeroomName != other.storeroomName) return false

        return true
    }

    override fun hashCode(): Int {
        var result = storeroomId.hashCode()
        result = 31 * result + ownerId.hashCode()
        result = 31 * result + storeroomName.hashCode()
        return result
    }

    override fun toString(): String {
        return "CreateStoreroomCommand(storeroomId='$storeroomId', ownerId='$ownerId', storeroomName='$storeroomName')"
    }


}