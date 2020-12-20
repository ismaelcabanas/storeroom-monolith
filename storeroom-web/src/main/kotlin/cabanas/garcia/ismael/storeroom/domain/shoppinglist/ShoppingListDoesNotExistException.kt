package cabanas.garcia.ismael.storeroom.domain.shoppinglist

class ShoppingListDoesNotExistException(id: ShoppingListId): Exception("ShoppingList '${id.value}' does not exist") {
}