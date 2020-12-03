package cabanas.garcia.ismael.storeroom.application.shoppinglist.get

import cabanas.garcia.ismael.storeroom.application.shared.bus.query.Query

data class GetShoppingListQuery(val shoppingListId: String, val userId: String): Query