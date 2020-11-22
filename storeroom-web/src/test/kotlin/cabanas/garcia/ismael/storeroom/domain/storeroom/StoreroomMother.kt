package cabanas.garcia.ismael.storeroom.domain.storeroom

import java.util.*

class StoreroomMother {
    companion object {
        fun createStoreroomWithProducts(products: Set<Product>): Storeroom {
            return Storeroom(StoreroomId(UUID.randomUUID().toString()), UserId(UUID.randomUUID().toString()), "Test Storeroom", products)
        }

        fun emptyStoreroom(): Storeroom {
            return createStoreroomWithProducts(emptySet())
        }
    }

}