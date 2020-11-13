package cabanas.garcia.ismael.storeroom.infrastructure.framework.controller.v1

import cabanas.garcia.ismael.storeroom.domain.productcatalog.api.CreateProduct
import cabanas.garcia.ismael.storeroom.domain.productcatalog.ProductDetails
import cabanas.garcia.ismael.storeroom.domain.productcatalog.ProductId
import cabanas.garcia.ismael.storeroom.domain.productcatalog.UserId
import cabanas.garcia.ismael.storeroom.infrastructure.framework.controller.v1.request.CreateProductRequest
import org.springframework.http.ResponseEntity

import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestBody

@RestController
@RequestMapping(path = ["/v1/products"])
class ProductCatalogController(private val createProduct: CreateProduct) {

    @PostMapping
    fun createProduct(
            @RequestHeader("User-Id") userId: String,
            @RequestBody request: CreateProductRequest): ResponseEntity<Void> {
        createProduct.byUserWithDetails(UserId(userId), ProductDetails(ProductId(request.productId), request.productName))

        val location = linkTo(this::class.java).slash(request.productId).toUri()

        return ResponseEntity.created(location).contentType(MediaType.APPLICATION_JSON).build()
    }
}