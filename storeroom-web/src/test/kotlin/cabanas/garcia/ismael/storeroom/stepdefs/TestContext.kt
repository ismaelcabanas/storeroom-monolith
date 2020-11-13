package cabanas.garcia.ismael.storeroom.stepdefs

import cabanas.garcia.ismael.storeroom.application.product.createproduct.CreateProductCommand
import cabanas.garcia.ismael.storeroom.domain.product.Product
import cabanas.garcia.ismael.storeroom.domain.product.ProductDetails

class TestContext() {
    lateinit var createProductCommand: CreateProductCommand
    lateinit var userId: String
    var error: Exception? = null
}