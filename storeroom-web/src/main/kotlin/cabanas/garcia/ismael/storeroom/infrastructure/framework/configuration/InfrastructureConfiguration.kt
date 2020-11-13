package cabanas.garcia.ismael.storeroom.infrastructure.framework.configuration

import cabanas.garcia.ismael.storeroom.domain.productcatalog.ProductRepository
import cabanas.garcia.ismael.storeroom.domain.productcatalog.spi.stubs.InMemoryProductRepository
import cabanas.garcia.ismael.storeroom.infrastructure.framework.repository.SpringJpaProductRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class InfrastructureConfiguration {
    @Bean
    fun productRepository(
            springJpaRepository: SpringJpaProductRepository
    ): ProductRepository = InMemoryProductRepository()
}