package cabanas.garcia.ismael.storeroom.stubs

import cabanas.garcia.ismael.storeroom.domain.product.ProductRepository
import cabanas.garcia.ismael.storeroom.domain.spi.stubs.InMemoryProductRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Profile

class StubsConfiguration {
    @Bean
    @Profile("stubs")
    fun productRepositoryStub(): ProductRepository = InMemoryProductRepository()
}