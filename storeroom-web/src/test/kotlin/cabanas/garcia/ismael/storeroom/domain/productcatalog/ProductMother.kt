package cabanas.garcia.ismael.storeroom.domain.productcatalog

import java.util.*

object ProductMother {
    private val ID = UUID.randomUUID().toString()
    private val CREATOR_ID = UUID.randomUUID().toString()
    private const val NAME = "some product name"

    fun aProduct() =
            Product(ProductId(ID), UserId(CREATOR_ID), NAME)

}