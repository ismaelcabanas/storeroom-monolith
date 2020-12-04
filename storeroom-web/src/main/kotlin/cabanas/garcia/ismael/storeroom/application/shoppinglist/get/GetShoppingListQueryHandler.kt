package cabanas.garcia.ismael.storeroom.application.shoppinglist.get

import cabanas.garcia.ismael.storeroom.application.shared.bus.query.QueryHandler
import cabanas.garcia.ismael.storeroom.domain.shoppinglist.Product
import cabanas.garcia.ismael.storeroom.domain.shoppinglist.ShoppingList
import cabanas.garcia.ismael.storeroom.domain.shoppinglist.ShoppingListDoesNotExistException
import cabanas.garcia.ismael.storeroom.domain.shoppinglist.ShoppingListRepository

class GetShoppingListQueryHandler(private val repository: ShoppingListRepository): QueryHandler<GetShoppingListQuery, ShoppingListResponse> {

    override fun handle(query: GetShoppingListQuery): ShoppingListResponse =
            mapToResponse(repository.findById(query.shoppingListId))

    private fun mapToResponse(shoppingList: ShoppingList) =
        ShoppingListResponse(shoppingList.id.value, mapToResponse(shoppingList.products()))

    private fun mapToResponse(products: List<Product>): List<ProductResponse> =
        products.map { ProductResponse(it.id.value, it.name, it.bought) }
}
