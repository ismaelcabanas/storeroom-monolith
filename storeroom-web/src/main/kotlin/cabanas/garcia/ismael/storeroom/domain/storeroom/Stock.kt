package cabanas.garcia.ismael.storeroom.domain.storeroom

class Stock(val value: Int) {
    fun add(quantity: Int): Stock {
        return Stock(value + quantity)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Stock

        if (value != other.value) return false

        return true
    }

    override fun hashCode(): Int {
        return value
    }

    override fun toString(): String {
        return "Stock(value=$value)"
    }


}