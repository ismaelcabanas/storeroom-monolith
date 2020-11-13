package cabanas.garcia.ismael.storeroom.stepdefs

import cabanas.garcia.ismael.storeroom.application.productcatalog.createproduct.CreateProductCommand

class TestContext() {
    lateinit var createProductCommand: CreateProductCommand
    lateinit var userId: String
    var error: Exception? = null
}