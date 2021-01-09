package cabanas.garcia.ismael.storeroom.domain.storeroom

import cabanas.garcia.ismael.storeroom.domain.storeroom.exception.ConsumeProductStockExceededException
import cabanas.garcia.ismael.storeroom.domain.storeroom.exception.NegativeStockException

class Product(id: String, stock: Int) {

    val id: ProductId = ProductId(id)
    val stock: Stock = Stock(stock)

    internal fun addStock(quantity: Int): Product {
        return Product(id.value, stock.increase(quantity).value)
    }

    internal fun consumeStock(quantity: Int): Product {
        try {
            return Product(id.value, stock.decrease(quantity).value)
        } catch (e: NegativeStockException) {
            throw ConsumeProductStockExceededException(id.value, stock(), quantity, e)
        }
    }

    internal fun stock(): Int {
        return stock.value
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Product

        if (id != other.id) return false
        if (stock != other.stock) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + stock.hashCode()
        return result
    }

    override fun toString(): String {
        return "Product(id=$id, stock=$stock)"
    }

}