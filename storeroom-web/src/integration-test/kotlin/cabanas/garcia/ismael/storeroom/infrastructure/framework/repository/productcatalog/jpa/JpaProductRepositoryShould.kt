package cabanas.garcia.ismael.storeroom.infrastructure.framework.repository.productcatalog.jpa

import cabanas.garcia.ismael.storeroom.infrastructure.framework.StoreroomWebApplication
import cabanas.garcia.ismael.storeroom.infrastructure.framework.entity.Product
import cabanas.garcia.ismael.storeroom.infrastructure.framework.repository.SpringJpaProductRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Profile
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import java.util.UUID
import java.util.Optional

@Testcontainers
@ExtendWith(SpringExtension::class)
@DataJpaTest
@ActiveProfiles("integration-test")
class JpaProductRepositoryShould @Autowired constructor(
        val entityManager: TestEntityManager,
        val productRepository: SpringJpaProductRepository) {

    companion object {
        @Container
        val container = PostgreSQLContainer<Nothing>("postgres:10.9")
                .apply {
                    withDatabaseName("storeroom")
                    withUsername("postgres")
                    withPassword("postgres")
                }

        @JvmStatic
        @DynamicPropertySource
        fun properties(registry: DynamicPropertyRegistry) {
            registry.add("spring.datasource.url", container::getJdbcUrl);
            registry.add("spring.datasource.password", container::getPassword);
            registry.add("spring.datasource.username", container::getUsername);
        }
    }

    @Test
    fun `When find by id then return product`() {
        val canOfCorn = Product(UUID.fromString("eaf199da-524b-48ff-b7d5-b46d38693709"), "Lata de maíz")
        entityManager.persist(canOfCorn)
        entityManager.flush()

        val actual = productRepository.findById(UUID.fromString("eaf199da-524b-48ff-b7d5-b46d38693709"))

        assertThat(actual).isEqualTo(Optional.of(canOfCorn))
    }

    @Test
    fun `Save product successfully`() {
        val productId = UUID.fromString("eaf199da-524b-48ff-b7d5-b46d38693709")
        val canOfCorn = Product(productId, "Lata de maíz")

        productRepository.save(canOfCorn)

        val actual = productRepository.findById(UUID.fromString("eaf199da-524b-48ff-b7d5-b46d38693709"))
        assertThat(actual).isNotEmpty
        assertThat(actual.get().id).isEqualTo(canOfCorn.id)
        assertThat(actual.get().name).isEqualTo(canOfCorn.name)
    }
}
