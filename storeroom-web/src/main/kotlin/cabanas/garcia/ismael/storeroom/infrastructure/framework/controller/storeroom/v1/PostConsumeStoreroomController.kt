package cabanas.garcia.ismael.storeroom.infrastructure.framework.controller.storeroom.v1

import cabanas.garcia.ismael.storeroom.application.shared.bus.command.CommandBus
import cabanas.garcia.ismael.storeroom.application.storeroom.consumeproduct.ConsumeProductCommand
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/storerooms/{id}/consume")
class PostConsumeStoreroomController(private val commandBus: CommandBus) {
    @PostMapping
    fun execute(
            @RequestHeader("User-Id") userId: String,
            @PathVariable("id") storeroomId: String,
            @RequestBody requestBody: PostConsumeStoreroomRequest) : ResponseEntity<Void> {

        commandBus.dispatch(mapToCommand(storeroomId, userId, requestBody))

        return ResponseEntity.ok().build()
    }

    private fun mapToCommand(storeroomId: String, userId: String, requestBody: PostConsumeStoreroomRequest) =
            ConsumeProductCommand(
                    storeroomId,
                    requestBody.productId,
                    requestBody.quantity,
                    userId)
}

data class PostConsumeStoreroomRequest (
        val productId: String,
        val quantity: Int
)