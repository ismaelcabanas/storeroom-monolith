package cabanas.garcia.ismael.storeroom.domain.shoppinglist

class ProductAlreadyExitsException(productId: String)
    : Exception("Product '$productId' already exist in the shopping list") {

}
