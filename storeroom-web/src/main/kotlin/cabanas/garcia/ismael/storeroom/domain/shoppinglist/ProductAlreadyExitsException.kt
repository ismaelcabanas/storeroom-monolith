package cabanas.garcia.ismael.storeroom.domain.shoppinglist

class ProductAlreadyExitsException(productId: ProductId)
    : Exception("Product '${productId.value}' already exist in the shopping list") {

}
