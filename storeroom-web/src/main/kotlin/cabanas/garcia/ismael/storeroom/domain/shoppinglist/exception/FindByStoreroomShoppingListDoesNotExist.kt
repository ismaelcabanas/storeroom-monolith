package cabanas.garcia.ismael.storeroom.domain.shoppinglist.exception

import cabanas.garcia.ismael.storeroom.domain.shoppinglist.StoreroomId

class FindByStoreroomShoppingListDoesNotExist(storeroomId: StoreroomId) : Exception("ShoppingList for storeroom '${storeroomId.value}' does not exist")
