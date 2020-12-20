package cabanas.garcia.ismael.storeroom.application.shoppinglist.get

import cabanas.garcia.ismael.storeroom.application.shared.bus.query.QueryHandler
import cabanas.garcia.ismael.storeroom.domain.shoppinglist.Product
import cabanas.garcia.ismael.storeroom.domain.shoppinglist.ShoppingList
import cabanas.garcia.ismael.storeroom.domain.shoppinglist.ShoppingListId
import cabanas.garcia.ismael.storeroom.domain.shoppinglist.ShoppingListRepository

class GetShoppingListQueryHandler(private val repository: ShoppingListRepository): QueryHandler<GetShoppingListQuery, ShoppingListResponse> {

    override fun handle(query: GetShoppingListQuery): ShoppingListResponse =
            mapToResponse(repository.findBy(ShoppingListId( query.shoppingListId))) ?: ShoppingListResponse(query.shoppingListId, emptyList())

    private fun mapToResponse(shoppingList: ShoppingList?): ShoppingListResponse? {
        return if (shoppingList == null) {
            null
        } else
            ShoppingListResponse(shoppingList.id.value, mapToResponse(shoppingList.products()))
    }

    private fun mapToResponse(products: List<Product>): List<ProductResponse> =
        products.map { ProductResponse(it.id.value, it.name, it.bought) }
}
