package cabanas.garcia.ismael.storeroom.domain.shoppinglist

interface ShoppingListRepository {
    fun findBy(id: ShoppingListId): ShoppingList?
    fun findBy(id: StoreroomId): ShoppingList?
    fun save(shoppingList: ShoppingList)
}