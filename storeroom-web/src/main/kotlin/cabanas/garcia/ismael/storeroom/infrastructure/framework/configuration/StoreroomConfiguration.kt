package cabanas.garcia.ismael.storeroom.infrastructure.framework.configuration

import cabanas.garcia.ismael.storeroom.domain.ProductRepository
import cabanas.garcia.ismael.storeroom.infrastructure.framework.entity.Product
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.*

@Configuration
class StoreroomConfiguration {
    @Bean
    fun databaseInitializer(productRepository: ProductRepository) = ApplicationRunner {
        productRepository.save(Product(UUID.randomUUID(), "Lata de maíz"))
        productRepository.save(Product(UUID.randomUUID(), "Lata de guisantes"))
    }
}