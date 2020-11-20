package cabanas.garcia.ismael.storeroom.domain.storeroom

class StoreroomDoesNotExistException(storeroomId: String)
    : Exception("Storeroom '$storeroomId' does not exist.") {
}