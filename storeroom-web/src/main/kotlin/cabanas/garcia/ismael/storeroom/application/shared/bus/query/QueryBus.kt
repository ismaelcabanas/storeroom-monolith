package cabanas.garcia.ismael.storeroom.application.shared.bus.query

interface QueryBus {
    fun <Q: Query, R: Response> ask(query: Q): R
}