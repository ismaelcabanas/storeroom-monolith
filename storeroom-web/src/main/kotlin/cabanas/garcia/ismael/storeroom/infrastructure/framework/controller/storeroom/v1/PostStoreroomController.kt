package cabanas.garcia.ismael.storeroom.infrastructure.framework.controller.storeroom.v1

import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/storerooms")
class PostStoreroomController {
    @PostMapping
    fun execute(
            @RequestHeader("User-Id") userId: String,
            @RequestBody requestBody: PostStoreroomRequest) : ResponseEntity<PostStoreroomResponse> {
        val location = WebMvcLinkBuilder.linkTo(this::class.java).slash(requestBody.id).toUri()
        return ResponseEntity.created(location(requestBody.id)).body(PostStoreroomResponse(requestBody.id, requestBody.name))
    }

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