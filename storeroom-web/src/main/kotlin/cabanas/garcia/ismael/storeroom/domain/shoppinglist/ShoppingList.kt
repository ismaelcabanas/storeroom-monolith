package cabanas.garcia.ismael.storeroom.domain.shoppinglist


class ShoppingList internal constructor(
        val id: ShoppingListId,
        val storeroomId: StoreroomId,
        val ownerId: UserId) {
    companion object {
        fun create(shoppingListId: String, storeroomId: String, ownerId: String): ShoppingList {
            return ShoppingList(ShoppingListId(shoppingListId), StoreroomId(storeroomId), UserId(ownerId))
        }
    }
}