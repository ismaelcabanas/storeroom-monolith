package cabanas.garcia.ismael.storeroom.infrastructure.framework.configuration

import cabanas.garcia.ismael.storeroom.application.productcatalog.createproduct.CreateProductCommandHandler
import cabanas.garcia.ismael.storeroom.domain.productcatalog.api.CreateProduct
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CommandHandlersConfiguration {
    @Bean
    fun createProductCommandHandler(createProduct: CreateProduct) = CreateProductCommandHandler(createProduct)
}