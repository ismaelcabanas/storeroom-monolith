package cabanas.garcia.ismael.storeroom.domain.product

import java.lang.RuntimeException

class ProductAlreadyExistsException(productName: String) : RuntimeException("A product already exists with name $productName") {
}