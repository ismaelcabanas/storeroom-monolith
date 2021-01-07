package cabanas.garcia.ismael.storeroom.infrastructure.framework.configuration

import cabanas.garcia.ismael.storeroom.application.shoppinglist.get.GetShoppingListQueryHandler
import cabanas.garcia.ismael.storeroom.domain.shoppinglist.ShoppingListRepository
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class QueryHandlersConfiguration {
    @Bean
    fun getShoppingListQueryHandler(
            @Qualifier("jpaShoppingListRepository")
            repository: ShoppingListRepository
    ) = GetShoppingListQueryHandler(repository)
}