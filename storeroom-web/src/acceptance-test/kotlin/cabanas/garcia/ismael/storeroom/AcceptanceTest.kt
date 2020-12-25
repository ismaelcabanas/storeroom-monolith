package cabanas.garcia.ismael.storeroom

import cabanas.garcia.ismael.storeroom.infrastructure.framework.Application
import cabanas.garcia.ismael.storeroom.testcontainers.TestcontainersInitializer
import io.restassured.module.mockmvc.RestAssuredMockMvc
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc

@ExtendWith(SpringExtension::class)
@SpringBootTest(
        classes = [Application::class]
)
@AutoConfigureMockMvc
@ContextConfiguration(initializers = [TestcontainersInitializer::class])
abstract class AcceptanceTest {
    @Autowired
    private lateinit var mvc: MockMvc

    @Autowired
    lateinit var jdbcTemplate: JdbcTemplate

    @BeforeEach
    fun setUp() {
        RestAssuredMockMvc.mockMvc(mvc)
    }
}