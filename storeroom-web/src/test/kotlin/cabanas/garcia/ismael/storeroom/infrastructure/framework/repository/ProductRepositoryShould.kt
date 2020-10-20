package cabanas.garcia.ismael.storeroom.infrastructure.framework.repository

import cabanas.garcia.ismael.storeroom.infrastructure.framework.entity.Product
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import java.util.UUID
import java.util.Optional

@DataJpaTest()
class ProductRepositoryShould @Autowired constructor(
        val entityManager: TestEntityManager,
        val productRepository: SpringJpaProductRepository) {

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
