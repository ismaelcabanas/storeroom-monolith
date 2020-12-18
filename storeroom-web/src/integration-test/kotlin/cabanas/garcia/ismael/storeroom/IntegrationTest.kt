package cabanas.garcia.ismael.storeroom

import cabanas.garcia.ismael.storeroom.infrastructure.framework.StoreroomWebApplication
import cabanas.garcia.ismael.storeroom.infrastructure.testcontainers.TestcontainersInitializer
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest(
        classes = [StoreroomWebApplication::class],
        properties = ["spring.profiles.active=integration-test"]
)
@ContextConfiguration(initializers = [TestcontainersInitializer::class])
abstract class IntegrationTest {

}