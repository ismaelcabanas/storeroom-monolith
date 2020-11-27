package cabanas.garcia.ismael.storeroom.infrastructure.framework.controller.storeroom.v1

import cabanas.garcia.ismael.storeroom.application.shared.CommandBus
import cabanas.garcia.ismael.storeroom.application.storeroom.replenishproduct.ReplenishProductCommand
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/storerooms/{id}/replenish")
class PostReplenishStoreroomController(private val commandBus: CommandBus) {
    @PostMapping
    fun execute(
            @RequestHeader("User-Id") userId: String,
            @PathVariable("id") storeroomId: String,
            @RequestBody requestBody: PostReplenishStoreroomRequest) : ResponseEntity<Void> {

        commandBus.dispatch(mapToCommand(storeroomId, userId, requestBody))

        return ResponseEntity.ok().build()
    }

    private fun mapToCommand(storeroomId: String, userId: String, requestBody: PostReplenishStoreroomRequest) =
            ReplenishProductCommand(
                storeroomId,
                requestBody.productId,
                requestBody.quantity,
                userId)
}

data class PostReplenishStoreroomRequest (
    val productId: String,
    val quantity: Int
)