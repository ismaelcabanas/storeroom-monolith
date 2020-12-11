package cabanas.garcia.ismael.storeroom.infrastructure.framework

import cabanas.garcia.ismael.storeroom.infrastructure.framework.repository.storeroom.jpa.JpaStoreroomRepositoryShould
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@Testcontainers
@ExtendWith(SpringExtension::class)
@SpringBootTest(
		classes = [StoreroomWebApplication::class]
)
class StoreroomWebApplicationShould {

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
	fun contextLoads() {
	}

}
