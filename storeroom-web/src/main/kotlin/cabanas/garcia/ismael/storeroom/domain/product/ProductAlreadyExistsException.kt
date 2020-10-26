package cabanas.garcia.ismael.storeroom.domain.product

class ProductAlreadyExistsException(productId: ProductId) : RuntimeException("A product already exists for the id $productId")
