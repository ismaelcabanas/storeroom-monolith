package cabanas.garcia.ismael.storeroom.infrastructure.framework.controller.storeroom.v1

import cabanas.garcia.ismael.storeroom.application.shared.CommandBus
import cabanas.garcia.ismael.storeroom.application.storeroom.createstoreroom.CreateStoreroomCommand
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/storerooms")
class PostStoreroomController(private val commandBus: CommandBus) {
    @PostMapping
    fun execute(
            @RequestHeader("User-Id") userId: String,
            @RequestBody requestBody: PostStoreroomRequest) : ResponseEntity<PostStoreroomResponse> {

        commandBus.dispatch(mapToCommand(userId, requestBody))

        return ResponseEntity.created(location(requestBody.id)).body(PostStoreroomResponse(requestBody.id, requestBody.name))
    }

    private fun mapToCommand(userId: String, requestBody: PostStoreroomRequest) = CreateStoreroomCommand(
            requestBody.id,
            userId,
            requestBody.name
    )

    private fun location(id: String) = WebMvcLinkBuilder.linkTo(this::class.java).slash(id).toUri()
}

data class PostStoreroomRequest(
        val id: String,
        val name: String
)

data class PostStoreroomResponse(
        val id: String,
        val name: String
)