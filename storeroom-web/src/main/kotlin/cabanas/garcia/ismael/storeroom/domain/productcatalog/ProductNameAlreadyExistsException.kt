package cabanas.garcia.ismael.storeroom.domain.productcatalog

class ProductNameAlreadyExistsException(productName: String) : Exception("A product already exists with name $productName") {
}