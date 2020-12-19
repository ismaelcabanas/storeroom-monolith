package cabanas.garcia.ismael.storeroom.domain.storeroom

import java.util.UUID

object StoreroomMother {
    fun aStoreroomWithProducts(products: Set<Product>) =
        Storeroom(StoreroomId(
                UUID.randomUUID().toString()),
                UserId(UUID.randomUUID().toString()),
                ProductNameMother.random(),
                products
        )

    fun aStoreroomWithProducts() =
        aStoreroomWithProducts(setOf(ProductMother.aProduct()))

    fun emptyStoreroom() =
        aStoreroomWithProducts(emptySet())

}