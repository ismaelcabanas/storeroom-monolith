package cabanas.garcia.ismael.storeroom

import cabanas.garcia.ismael.storeroom.infrastructure.framework.Application
import cabanas.garcia.ismael.storeroom.infrastructure.testcontainers.TestcontainersInitializer
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration

@SpringBootTest(
        classes = [Application::class],
        properties = ["spring.profiles.active=integration-test"]
)
@ContextConfiguration(initializers = [TestcontainersInitializer::class])
abstract class IntegrationTest