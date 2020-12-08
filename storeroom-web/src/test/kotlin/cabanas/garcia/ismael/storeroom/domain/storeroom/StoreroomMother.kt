package cabanas.garcia.ismael.storeroom.domain.storeroom

import java.util.*

object StoreroomMother {
    private val ID = UUID.randomUUID().toString()
    private val OWNER_ID = UUID.randomUUID().toString()
    private const val NAME = "some storeroom name"

    fun createStoreroomWithProducts(products: Set<Product>): Storeroom {
        return Storeroom(StoreroomId(UUID.randomUUID().toString()), UserId(UUID.randomUUID().toString()), "Test Storeroom", products)
    }

    fun emptyStoreroom(): Storeroom {
        return createStoreroomWithProducts(emptySet())
    }

    fun aStoreroom() =
            Storeroom(StoreroomId(ID), UserId(OWNER_ID), NAME)

}