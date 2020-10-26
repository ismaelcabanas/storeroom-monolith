package cabanas.garcia.ismael.storeroom.infrastructure.framework.configuration

import cabanas.garcia.ismael.storeroom.domain.product.ProductRepository
import cabanas.garcia.ismael.storeroom.infrastructure.framework.repository.JpaProductRepository
import cabanas.garcia.ismael.storeroom.infrastructure.framework.repository.SpringJpaProductRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class InfrastructureConfiguration {
    @Bean
    fun productRepository(
            springJpaRepository: SpringJpaProductRepository
    ): ProductRepository = JpaProductRepository(springJpaRepository)
}