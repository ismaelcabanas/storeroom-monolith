package cabanas.garcia.ismael.storeroom.stepdefs

import cabanas.garcia.ismael.storeroom.domain.product.Product
import cabanas.garcia.ismael.storeroom.domain.product.ProductDetails

class TestContext() {
    lateinit var requestedProductDetails: ProductDetails
    lateinit var userId: String
    lateinit var createdProduct: Product
    var error: Exception? = null
}