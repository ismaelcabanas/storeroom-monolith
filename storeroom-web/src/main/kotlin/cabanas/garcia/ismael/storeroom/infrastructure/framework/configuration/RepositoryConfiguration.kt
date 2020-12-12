package cabanas.garcia.ismael.storeroom.infrastructure.framework.configuration

import cabanas.garcia.ismael.storeroom.infrastructure.framework.repository.productcatalog.jpa.JpaProductRepository
import cabanas.garcia.ismael.storeroom.infrastructure.framework.repository.productcatalog.jpa.spring.SpringJpaProductRepository
import cabanas.garcia.ismael.storeroom.infrastructure.framework.repository.shoppinglist.InMemoryShoppingListRepository
import cabanas.garcia.ismael.storeroom.infrastructure.framework.repository.storeroom.InMemoryStoreroomRepository
import cabanas.garcia.ismael.storeroom.infrastructure.framework.repository.storeroom.jpa.JpaStoreroomRepository
import cabanas.garcia.ismael.storeroom.infrastructure.framework.repository.storeroom.jpa.SpringJpaStoreroomRepository
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
    fun jpaProductCatalogRepository(springJpaRepository: SpringJpaProductRepository) =
            JpaProductRepository(springJpaRepository)
    @Bean
    @Qualifier("jpaStoreroomRepository")
    fun jpaStoreroomRepository(springJpaRepository: SpringJpaStoreroomRepository) =
            JpaStoreroomRepository(springJpaRepository)
}