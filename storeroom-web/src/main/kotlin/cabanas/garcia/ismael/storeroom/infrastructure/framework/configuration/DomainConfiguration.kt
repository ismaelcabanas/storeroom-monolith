package cabanas.garcia.ismael.storeroom.infrastructure.framework.configuration

import cabanas.garcia.ismael.storeroom.domain.product.ProductCreator
import cabanas.garcia.ismael.storeroom.domain.product.ProductRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DomainConfiguration {

    @Bean
    fun createProduct(productRepository: ProductRepository) = ProductCreator(productRepository)
}