package cabanas.garcia.ismael.storeroom.domain.storeroom

class Product(val id: ProductId, val stock: Int) {

    fun addStock(quantity: Int): Product {
        return Product(id, stock + quantity)
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
        result = 31 * result + stock
        return result
    }

    override fun toString(): String {
        return "Product(id=$id, stock=$stock)"
    }
}