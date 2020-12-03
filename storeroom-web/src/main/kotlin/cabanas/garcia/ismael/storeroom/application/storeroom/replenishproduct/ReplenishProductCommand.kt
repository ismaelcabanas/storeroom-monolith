package cabanas.garcia.ismael.storeroom.application.storeroom.replenishproduct

import cabanas.garcia.ismael.storeroom.application.shared.bus.command.Command

data class ReplenishProductCommand(val storeroomId: String,
                                   val productId: String,
                                   val quantity: Int,
                                   val userId: String): Command