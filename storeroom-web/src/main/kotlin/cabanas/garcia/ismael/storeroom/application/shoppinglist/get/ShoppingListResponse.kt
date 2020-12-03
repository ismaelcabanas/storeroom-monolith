package cabanas.garcia.ismael.storeroom.application.shoppinglist.get

import cabanas.garcia.ismael.storeroom.application.shared.bus.query.Response

data class ShoppingListResponse(val id: String, val products: List<ProductResponse>): Response