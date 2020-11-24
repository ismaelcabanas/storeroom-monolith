package cabanas.garcia.ismael.storeroom.domain.shoppinglist

class ShoppingListDoesNotExistException(shoppingListId: String): Exception("ShoppingList '$shoppingListId' does not exist") {
}