package cabanas.garcia.ismael.storeroom.domain.storeroom

import java.util.UUID

object StoreroomMother {
    fun aStoreroomWithProducts(
            storeroomId: String = UUID.randomUUID().toString(),
            ownerId: String = UUID.randomUUID().toString(),
            storeroomName: String = "some storeroom name",
            products: List<Product> = emptyList()) =
        Storeroom(
                storeroomId,
                ownerId,
                storeroomName,
                products
        )

    fun emptyStoreroom(
            storeroomId: String = UUID.randomUUID().toString(),
            ownerId: String = UUID.randomUUID().toString(),
            storeroomName: String = "some storeroom name") =
        aStoreroomWithProducts(storeroomId, ownerId, storeroomName, emptyList())

}