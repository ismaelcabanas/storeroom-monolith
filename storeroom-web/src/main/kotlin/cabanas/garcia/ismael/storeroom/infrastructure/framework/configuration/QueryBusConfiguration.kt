package cabanas.garcia.ismael.storeroom.infrastructure.framework.configuration

import cabanas.garcia.ismael.storeroom.application.shared.bus.query.Query
import cabanas.garcia.ismael.storeroom.application.shared.bus.query.QueryBus
import cabanas.garcia.ismael.storeroom.application.shared.bus.query.QueryHandler
import cabanas.garcia.ismael.storeroom.application.shared.bus.query.Response
import cabanas.garcia.ismael.storeroom.application.shoppinglist.get.GetShoppingListQuery
import cabanas.garcia.ismael.storeroom.application.shoppinglist.get.GetShoppingListQueryHandler
import cabanas.garcia.ismael.storeroom.infrastructure.shared.bus.InMemoryQueryBus
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class QueryBusConfiguration {
    @Bean
    fun inMemoryQueryBus(getShoppingListQueryHandler: GetShoppingListQueryHandler): QueryBus {
        InMemoryQueryBus.registry[GetShoppingListQuery::class.java.simpleName] = getShoppingListQueryHandler as QueryHandler<Query, Response>

        return InMemoryQueryBus
    }
}