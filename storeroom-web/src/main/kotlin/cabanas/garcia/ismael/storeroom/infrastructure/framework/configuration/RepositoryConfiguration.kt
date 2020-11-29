package cabanas.garcia.ismael.storeroom.infrastructure.framework.configuration

import cabanas.garcia.ismael.storeroom.infrastructure.framework.repository.storeroom.InMemoryStoreroomRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RepositoryConfiguration {
    @Bean
    fun storeroomRepository() = InMemoryStoreroomRepository()
}