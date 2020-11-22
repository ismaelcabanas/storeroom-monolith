package cabanas.garcia.ismael.storeroom.domain.storeroom.exception

class StoreroomDoesNotExistException(storeroomId: String)
    : Exception("Storeroom '$storeroomId' does not exist.") {
}