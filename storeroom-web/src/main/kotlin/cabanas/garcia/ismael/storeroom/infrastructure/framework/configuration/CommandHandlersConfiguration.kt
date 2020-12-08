package cabanas.garcia.ismael.storeroom.infrastructure.framework.configuration

import cabanas.garcia.ismael.storeroom.application.productcatalog.createproduct.CreateProductCommandHandler
import cabanas.garcia.ismael.storeroom.application.storeroom.consumeproduct.ConsumeProductCommandHandler
import cabanas.garcia.ismael.storeroom.application.storeroom.createstoreroom.CreateStoreroomCommandHandler
import cabanas.garcia.ismael.storeroom.application.storeroom.replenishproduct.ReplenishProductCommandHandler
import cabanas.garcia.ismael.storeroom.domain.productcatalog.api.CreateProduct
import cabanas.garcia.ismael.storeroom.domain.shared.eventbus.EventBus
import cabanas.garcia.ismael.storeroom.domain.storeroom.StoreroomFactory
import cabanas.garcia.ismael.storeroom.domain.storeroom.StoreroomRepository
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CommandHandlersConfiguration {
    @Bean
    fun createProductCommandHandler(createProduct: CreateProduct) = CreateProductCommandHandler(createProduct)

    @Bean
    fun createStoreroomCommandHandler(
            storeroomFactory: StoreroomFactory,
            @Qualifier("jpaStoreroomRepository")
            storeroomRepository: StoreroomRepository,
            eventBus: EventBus
    ) = CreateStoreroomCommandHandler(storeroomFactory, storeroomRepository, eventBus)

    @Bean
    fun replenishProductCommandHandler(
            storeroomRepository: StoreroomRepository,
            eventBus: EventBus
    ) = ReplenishProductCommandHandler(storeroomRepository, eventBus)

    @Bean
    fun consumeProductCommandHandler(
            storeroomRepository: StoreroomRepository,
            eventBus: EventBus
    ) = ConsumeProductCommandHandler(storeroomRepository, eventBus)
}