package cabanas.garcia.ismael.storeroom.application

import java.lang.RuntimeException

class ApplicationError(message: String?, cause: Throwable?) : RuntimeException(message, cause) {
}