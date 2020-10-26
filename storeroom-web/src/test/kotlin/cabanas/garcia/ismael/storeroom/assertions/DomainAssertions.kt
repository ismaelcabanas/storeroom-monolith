package cabanas.garcia.ismael.storeroom.assertions

import cabanas.garcia.ismael.storeroom.domain.product.Product

val Product.that: ProductAssert
    get() = ProductAssert(this)