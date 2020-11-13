package cabanas.garcia.ismael.storeroom.stepdefs

import cabanas.garcia.ismael.storeroom.application.productcatalog.createproduct.CreateProductCommand
import cabanas.garcia.ismael.storeroom.application.storeroom.createstoreroom.CreateStoreroomCommand

class TestContext() {
    lateinit var createStoreroomCommand: CreateStoreroomCommand
    lateinit var createProductCommand: CreateProductCommand
    lateinit var userId: String
    var error: Exception? = null
}