package cabanas.garcia.ismael.storeroom.stubs

import cabanas.garcia.ismael.storeroom.domain.productcatalog.ProductRepository
import cabanas.garcia.ismael.storeroom.domain.productcatalog.spi.stubs.InMemoryProductRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Profile

class StubsConfiguration {
    @Bean
    @Profile("stubs")
    fun productRepositoryStub(): ProductRepository = InMemoryProductRepository()
}