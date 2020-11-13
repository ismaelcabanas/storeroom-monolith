package cabanas.garcia.ismael.storeroom.assertions

import cabanas.garcia.ismael.storeroom.domain.productcatalog.Product
import cabanas.garcia.ismael.storeroom.domain.storeroom.Storeroom

val Product.that: ProductAssert
    get() = ProductAssert(this)

val Storeroom.that: StoreroomAssert
    get() = StoreroomAssert(this)