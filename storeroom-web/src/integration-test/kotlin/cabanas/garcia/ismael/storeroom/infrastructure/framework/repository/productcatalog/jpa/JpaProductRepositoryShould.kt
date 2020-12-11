package cabanas.garcia.ismael.storeroom.infrastructure.framework.repository.productcatalog.jpa

import cabanas.garcia.ismael.storeroom.domain.productcatalog.ProductMother
import cabanas.garcia.ismael.storeroom.domain.productcatalog.ProductRepository
import cabanas.garcia.ismael.storeroom.infrastructure.framework.StoreroomWebApplication
import cabanas.garcia.ismael.storeroom.infrastructure.framework.repository.storeroom.jpa.JpaStoreroomRepositoryShould
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.jdbc.JdbcTestUtils
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@Testcontainers
@ExtendWith(SpringExtension::class)
@SpringBootTest(
        classes = [StoreroomWebApplication::class],
        properties = ["spring.profiles.active=integration-test"]
)
internal class JpaProductRepositoryShould @Autowired constructor(
        val productRepository: ProductRepository,
        val jdbcTemplate: JdbcTemplate) {

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

    @BeforeEach
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, statements = ["DELETE FROM PRODUCT"])
    internal fun `configure system under test`() {

    }

    @Test
    fun `save product`() {
        val aProduct = ProductMother.aProduct()

        productRepository.save(aProduct)

        assertThat(
                JdbcTestUtils.countRowsInTableWhere(
                        jdbcTemplate,
                        "PRODUCT",
                        "ID = '" + aProduct.id.value + "'"
                                + " AND CREATOR_ID = '" + aProduct.creatorId.value + "'"
                                + " AND NAME = '" + aProduct.name + "'"
                )
        ).isEqualTo(1)
    }
}
