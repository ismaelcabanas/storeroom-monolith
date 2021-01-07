package cabanas.garcia.ismael.storeroom.infrastructure.framework.configuration

import cabanas.garcia.ismael.storeroom.domain.productcatalog.ProductRepository
import cabanas.garcia.ismael.storeroom.domain.shoppinglist.ShoppingListRepository
import cabanas.garcia.ismael.storeroom.domain.storeroom.StoreroomRepository
import cabanas.garcia.ismael.storeroom.infrastructure.framework.repository.productcatalog.jpa.JpaProductRepository
import cabanas.garcia.ismael.storeroom.infrastructure.framework.repository.productcatalog.jpa.SpringJpaProductRepository
import cabanas.garcia.ismael.storeroom.infrastructure.framework.repository.shoppinglist.InMemoryShoppingListRepository
import cabanas.garcia.ismael.storeroom.infrastructure.framework.repository.shoppinglist.jpa.JpaShoppingListRepository
import cabanas.garcia.ismael.storeroom.infrastructure.framework.repository.shoppinglist.jpa.SpringJpaShoppingListRepository
import cabanas.garcia.ismael.storeroom.infrastructure.framework.repository.storeroom.jpa.JpaStoreroomRepository
import cabanas.garcia.ismael.storeroom.infrastructure.framework.repository.storeroom.jpa.SpringJpaStoreroomProductRepository
import cabanas.garcia.ismael.storeroom.infrastructure.framework.repository.storeroom.jpa.SpringJpaStoreroomRepository
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RepositoryConfiguration {
    @Bean
    fun shoppingListRepository() : ShoppingListRepository = InMemoryShoppingListRepository()

    @Bean
    fun jpaProductCatalogRepository(springJpaRepository: SpringJpaProductRepository) : ProductRepository =
            JpaProductRepository(springJpaRepository)
    @Bean
    @Qualifier("jpaStoreroomRepository")
    fun jpaStoreroomRepository(
            springJpaRepository: SpringJpaStoreroomRepository,
            springJpaStoreroomProductRepository: SpringJpaStoreroomProductRepository) : StoreroomRepository =
            JpaStoreroomRepository(springJpaRepository, springJpaStoreroomProductRepository)

    @Bean
    @Qualifier("jpaShoppingListRepository")
    fun jpaShoppingListRepository(
            springJpaShoppingListRepository: SpringJpaShoppingListRepository) : ShoppingListRepository =
            JpaShoppingListRepository(springJpaShoppingListRepository)
}