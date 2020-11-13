package cabanas.garcia.ismael.storeroom.domain.storeroom

interface StoreroomFactory {
    fun create(storeroomId: String, ownerId: String, storeroomName: String): Storeroom
}
