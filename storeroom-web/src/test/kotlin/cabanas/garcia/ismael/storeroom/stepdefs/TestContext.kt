package cabanas.garcia.ismael.storeroom.stepdefs

import cabanas.garcia.ismael.storeroom.application.productcatalog.createproduct.CreateProductCommand
import cabanas.garcia.ismael.storeroom.domain.storeroom.Storeroom

class TestContext() {
    lateinit var productId: String
    lateinit var storeroom: Storeroom
    lateinit var createProductCommand: CreateProductCommand
    lateinit var userId: String
    var error: Exception? = null
}