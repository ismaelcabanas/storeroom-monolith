package cabanas.garcia.ismael.storeroom.assertions

import cabanas.garcia.ismael.storeroom.domain.productcatalog.Product

val Product.that: ProductAssert
    get() = ProductAssert(this)