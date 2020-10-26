package cabanas.garcia.ismael.storeroom.assertions

import cabanas.garcia.ismael.storeroom.domain.product.Product
import cabanas.garcia.ismael.storeroom.domain.product.UserId
import org.assertj.core.api.AbstractAssert

class ProductAssert(actual: Product) : AbstractAssert<ProductAssert, Product>(actual, ProductAssert::class.java) {
    infix fun `corresponds to user`(userId: String) {
        matches({ actual.userId == UserId(userId) }, "product user id corresponds to userId")
    }

    infix fun `has name`(productName: String) {
        matches({ actual.name == productName }, "product name corresponds to productName")
    }
}