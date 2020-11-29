package cabanas.garcia.ismael.storeroom.infrastructure.framework.configuration

import cabanas.garcia.ismael.storeroom.domain.productcatalog.ProductCreator
import cabanas.garcia.ismael.storeroom.domain.productcatalog.ProductRepository
import cabanas.garcia.ismael.storeroom.domain.storeroom.spi.DefaultStoreroomFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DomainConfiguration {
    @Bean
    fun createProduct(productRepository: ProductRepository) = ProductCreator(productRepository)

    @Bean
    fun storeroomFactory() = DefaultStoreroomFactory()
}