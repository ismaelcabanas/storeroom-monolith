package cabanas.garcia.ismael.storeroom.infrastructure.framework.repository.storeroom

import cabanas.garcia.ismael.storeroom.domain.storeroom.StoreroomMother
import cabanas.garcia.ismael.storeroom.domain.storeroom.StoreroomRepository
import cabanas.garcia.ismael.storeroom.infrastructure.framework.StoreroomWebApplication
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.context.jdbc.Sql.ExecutionPhase
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.jdbc.JdbcTestUtils.countRowsInTableWhere
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers


@Testcontainers
@ExtendWith(SpringExtension::class)
@SpringBootTest(
        classes = [StoreroomWebApplication::class],
        properties = ["spring.profiles.active=integration-test"]
)
internal class JpaStoreroomRepositoryShould {

    @Autowired
    @Qualifier("jpaStoreroomRepository")
    private lateinit var repository: StoreroomRepository

    @Autowired
    private lateinit var jdbcTemplate: JdbcTemplate

    companion object {
        @Container
        val container = PostgreSQLContainer<Nothing>("postgres:10.9").apply {
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
    @Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, statements = ["DELETE FROM STOREROOM"])
    internal fun `configure system under test`() {

    }

    @AfterEach
    internal fun `tear down`() {
        container.stop()
    }

    @Test
    fun `save a storeroom`() {
        val aStoreroom = StoreroomMother.aStoreroom()

        repository.save(aStoreroom)

        assertThat(
                countRowsInTableWhere(
                        jdbcTemplate,
                        "STOREROOM",
                "ID = '" + aStoreroom.id.value + "'"
                        + " AND OWNER_ID = '" + aStoreroom.ownerId.value + "'"
                        + " AND NAME = '" + aStoreroom.name + "'"
                )
        ).isEqualTo(1)
    }
}