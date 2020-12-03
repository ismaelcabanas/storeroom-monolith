package cabanas.garcia.ismael.storeroom.infrastructure.framework.controller.shoppinglist.v1

import cabanas.garcia.ismael.storeroom.application.shared.bus.query.QueryBus
import cabanas.garcia.ismael.storeroom.application.shoppinglist.get.GetShoppingListQuery
import cabanas.garcia.ismael.storeroom.application.shoppinglist.get.ShoppingListResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/shoppinglists/{id}")
class GetShoppingListController(private val queryBus: QueryBus) {
    @GetMapping
    fun execute(
            @RequestHeader("User-Id") userId: String,
            @PathVariable("id") shoppingListId: String) : ResponseEntity<ShoppingListResponse> {
        return ResponseEntity.ok(queryBus.ask(GetShoppingListQuery(shoppingListId, userId)))
    }
}