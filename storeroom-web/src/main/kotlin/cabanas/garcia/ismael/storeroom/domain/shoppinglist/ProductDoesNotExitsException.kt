package cabanas.garcia.ismael.storeroom.domain.shoppinglist

class ProductDoesNotExitsException(productId: String)
    : Exception("Product '$productId' is not in the storeroom") {

}
