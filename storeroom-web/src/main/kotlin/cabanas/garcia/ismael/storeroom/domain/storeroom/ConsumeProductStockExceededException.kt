package cabanas.garcia.ismael.storeroom.domain.storeroom

class ConsumeProductStockExceededException(
        productId: String,
        currentStock: Int,
        consumeStock: Int,
        error: Throwable)
    : Exception("Product '$productId' stock is $currentStock and you want consume $consumeStock units of stock", error) {

}
