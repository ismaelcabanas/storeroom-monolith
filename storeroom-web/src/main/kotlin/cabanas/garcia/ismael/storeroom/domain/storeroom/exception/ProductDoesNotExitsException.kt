package cabanas.garcia.ismael.storeroom.domain.storeroom.exception

class ProductDoesNotExitsException(productId: String)
    : Exception("Product '$productId' is not in the storeroom") {

}
