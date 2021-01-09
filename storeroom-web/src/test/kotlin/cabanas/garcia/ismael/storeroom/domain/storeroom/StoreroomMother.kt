package cabanas.garcia.ismael.storeroom.domain.storeroom

import java.util.UUID

object StoreroomMother {
    fun aStoreroomWithProducts(products: List<Product>) =
        Storeroom(
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                ProductNameMother.random(),
                products
        )

    fun aStoreroomWithProducts() =
        aStoreroomWithProducts(listOf(ProductMother.aProduct()))

    fun emptyStoreroom(
            storeroomId: String = UUID.randomUUID().toString(),
            ownerId: String = UUID.randomUUID().toString(),
            storeroomName: String = ProductNameMother.random()) =
        Storeroom(storeroomId, ownerId, storeroomName)

}