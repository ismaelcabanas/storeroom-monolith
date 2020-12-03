package cabanas.garcia.ismael.storeroom.domain.shoppinglist

interface ShoppingListRepository {
    fun findById(shoppingListId: String): ShoppingList
    fun save(shoppingList: ShoppingList)
}