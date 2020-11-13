package cabanas.garcia.ismael.storeroom.infrastructure.framework.configuration

import cabanas.garcia.ismael.storeroom.domain.productcatalog.ProductRepository
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class StoreroomConfiguration {
    @Bean
    fun databaseInitializer(productRepository: ProductRepository) = ApplicationRunner {
        //productRepository.save(Product(UUID.randomUUID(), "Lata de maíz"))
        //productRepository.save(Product(UUID.randomUUID(), "Lata de guisantes"))
    }
}