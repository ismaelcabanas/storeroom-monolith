package cabanas.garcia.ismael.storeroom.assertions

import cabanas.garcia.ismael.storeroom.domain.productcatalog.Product
import cabanas.garcia.ismael.storeroom.domain.productcatalog.UserId
import org.assertj.core.api.AbstractAssert

class ProductAssert(actual: Product) : AbstractAssert<ProductAssert, Product>(actual, ProductAssert::class.java) {
    infix fun `corresponds to creator`(creatorId: String) {
        matches({ actual.creatorId == UserId(creatorId) }, "product owner id corresponds to creatorId")
    }

    infix fun `has name`(productName: String) {
        matches({ actual.name == productName }, "product name corresponds to productName")
    }

    infix fun `has id`(productId: String) {
        matches({actual.id.id == productId}, "product id corresponds to productId")
    }
}