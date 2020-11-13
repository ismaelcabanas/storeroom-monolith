package cabanas.garcia.ismael.storeroom.domain.product

class ProductNameAlreadyExistsException(productName: String) : Exception("A product already exists with name $productName") {
}