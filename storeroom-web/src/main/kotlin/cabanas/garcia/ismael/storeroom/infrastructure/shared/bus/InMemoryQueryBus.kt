package cabanas.garcia.ismael.storeroom.infrastructure.shared.bus

import cabanas.garcia.ismael.storeroom.application.shared.bus.query.Query
import cabanas.garcia.ismael.storeroom.application.shared.bus.query.QueryBus
import cabanas.garcia.ismael.storeroom.application.shared.bus.query.QueryHandler
import cabanas.garcia.ismael.storeroom.application.shared.bus.query.Response
import java.util.concurrent.ConcurrentHashMap

object InMemoryQueryBus: QueryBus {
    var registry: MutableMap<String, QueryHandler<Query, Response>> = ConcurrentHashMap()

    override fun <Q : Query, R : Response> ask(query: Q): R {
        val queryHandler = registry[query.javaClass.simpleName] as QueryHandler<Q, R>

        return queryHandler.handle(query)
    }
}