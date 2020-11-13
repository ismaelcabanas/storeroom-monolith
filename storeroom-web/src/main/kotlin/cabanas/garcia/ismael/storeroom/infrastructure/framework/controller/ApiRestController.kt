package cabanas.garcia.ismael.storeroom.infrastructure.framework.controller

import cabanas.garcia.ismael.storeroom.domain.productcatalog.ProductRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/products")
class ApiRestController(private val productRepository: ProductRepository) {
    @GetMapping("/")
    fun findAllProducts() =
            productRepository.findAll()
}