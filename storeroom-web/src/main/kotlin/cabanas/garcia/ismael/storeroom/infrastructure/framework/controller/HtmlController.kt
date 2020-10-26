package cabanas.garcia.ismael.storeroom.infrastructure.framework.controller

import cabanas.garcia.ismael.storeroom.domain.product.ProductRepository
import cabanas.garcia.ismael.storeroom.infrastructure.framework.entity.Product
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.server.ResponseStatusException
import java.util.*

@Controller
class HtmlController(private val repository: ProductRepository) {

    @GetMapping("/")
    fun index(model: Model) : String {
        model["title"] = "Storeroom"
        //model["products"] = repository.findAll().map {it.render()}
        return "storeroom"
    }

    @GetMapping("/products/{productId}")
    fun product(@PathVariable productId:String, model: Model): String {
        /*
        var product = repository.findById(UUID.fromString(productId))
                ?.render()
                ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "The product does not exist")
        model["title"] = product.name
        model["product"] = product

         */
        return "product"
    }

    @GetMapping("/products/add")
    fun addProduct(model: Model): String {
        model["title"] = "Add product"
        return "product"
    }

    fun Product.render() = RenderedProduct(id.toString(), name)

    data class RenderedProduct(
            val id: String,
            val name: String)
}