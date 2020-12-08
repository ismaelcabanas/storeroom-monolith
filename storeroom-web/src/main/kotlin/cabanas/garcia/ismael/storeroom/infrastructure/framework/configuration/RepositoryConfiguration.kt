package cabanas.garcia.ismael.storeroom.infrastructure.framework.configuration

import cabanas.garcia.ismael.storeroom.infrastructure.framework.repository.shoppinglist.InMemoryShoppingListRepository
import cabanas.garcia.ismael.storeroom.infrastructure.framework.repository.storeroom.InMemoryStoreroomRepository
import cabanas.garcia.ismael.storeroom.infrastructure.framework.repository.storeroom.jpa.JpaStoreroomRepository
import cabanas.garcia.ismael.storeroom.infrastructure.framework.repository.storeroom.jpa.spring.SpringJpaStoreroomRepository
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RepositoryConfiguration {
    @Bean
    fun storeroomRepository() = InMemoryStoreroomRepository()

    @Bean
    fun shoppingListRepository() = InMemoryShoppingListRepository()

    @Bean
    @Qualifier("jpaStoreroomRepository")
    fun jpaStoreroomRepository(springJpaRepository: SpringJpaStoreroomRepository) =
            JpaStoreroomRepository(springJpaRepository)
}