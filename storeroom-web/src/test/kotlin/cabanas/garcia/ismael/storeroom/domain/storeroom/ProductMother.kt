package cabanas.garcia.ismael.storeroom.domain.storeroom

import java.util.UUID

object ProductMother {
    private const val STOCK_10 = 10

    fun aProduct() =
            aProductWithStock(STOCK_10)

    fun aProductWithStock(stock: Int) =
            Product(UUID.randomUUID().toString(), stock)

}