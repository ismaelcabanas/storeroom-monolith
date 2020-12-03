package cabanas.garcia.ismael.storeroom.application.shared.bus.query

interface QueryHandler<Q : Query, R : Response> {
    fun handle(query: Q) : R
}