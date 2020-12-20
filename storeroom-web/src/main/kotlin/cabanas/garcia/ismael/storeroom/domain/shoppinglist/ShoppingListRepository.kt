package cabanas.garcia.ismael.storeroom.domain.shoppinglist

interface ShoppingListRepository {
    fun findBy(id: ShoppingListId): ShoppingList?
    fun save(shoppingList: ShoppingList)
}