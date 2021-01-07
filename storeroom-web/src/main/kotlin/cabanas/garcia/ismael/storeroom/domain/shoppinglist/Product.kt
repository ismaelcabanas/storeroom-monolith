package cabanas.garcia.ismael.storeroom.domain.shoppinglist

data class Product(val id: ProductId, val name: String, val bought: Boolean = false)