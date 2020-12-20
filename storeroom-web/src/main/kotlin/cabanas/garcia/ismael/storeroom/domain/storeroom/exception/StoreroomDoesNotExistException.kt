package cabanas.garcia.ismael.storeroom.domain.storeroom.exception

import cabanas.garcia.ismael.storeroom.domain.storeroom.StoreroomId

class StoreroomDoesNotExistException(storeroomId: StoreroomId)
    : Exception("Storeroom '${storeroomId.value}' does not exist.") {
}