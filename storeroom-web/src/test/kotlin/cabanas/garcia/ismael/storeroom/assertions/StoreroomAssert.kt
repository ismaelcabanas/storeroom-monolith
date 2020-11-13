package cabanas.garcia.ismael.storeroom.assertions

import cabanas.garcia.ismael.storeroom.domain.storeroom.Storeroom
import cabanas.garcia.ismael.storeroom.domain.storeroom.UserId
import org.assertj.core.api.AbstractAssert

class StoreroomAssert(actual: Storeroom) : AbstractAssert<StoreroomAssert, Storeroom>(actual, StoreroomAssert::class.java){
    infix fun `corresponds to owner`(ownerUser: String) {
        matches({ actual.ownerId == UserId(ownerUser) }, "storeroom owner id corresponds to ownerId")
    }

    infix fun `has name`(storeroomName: String) {
        matches({ actual.name == storeroomName }, "storeroom name corresponds to storeroomName")
    }

}
