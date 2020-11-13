package cabanas.garcia.ismael.storeroom.infrastructure.framework.configuration

import cabanas.garcia.ismael.storeroom.application.product.createproduct.CreateProductCommandHandler
import cabanas.garcia.ismael.storeroom.domain.product.api.CreateProduct
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CommandHandlersConfiguration {
    @Bean
    fun createProductCommandHandler(createProduct: CreateProduct) = CreateProductCommandHandler(createProduct)
}