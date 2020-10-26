package cabanas.garcia.ismael.storeroom.infrastructure.framework

import cabanas.garcia.ismael.storeroom.domain.product.ProductRepository
import cabanas.garcia.ismael.storeroom.infrastructure.framework.entity.Product
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.http.HttpStatus
import java.util.*

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HtmlControllersTests(
        @Autowired val restTemplate: TestRestTemplate,
        @Autowired val productRepository: ProductRepository) {

    companion object {
        @BeforeAll
        @JvmStatic
        fun setup() {
            println(">> Setup")
        }

        @AfterAll
        @JvmStatic
        fun teardown() {
            println(">> Tear down")
        }
    }

    @Test
    fun `Assert storeroom page title, content and status code`() {
        val entity = restTemplate.getForEntity<String>("/")

        assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(entity.body).contains("<h1>Storeroom</h1>")
    }

    @Test
    fun `Assert product page title, content and status code`() {
        val productId = UUID.randomUUID()
        val productName = "Lata de maíz"
        //productRepository.save(Product(productId, productName))

        val entity = restTemplate.getForEntity<String>("/products/${productId}")

        assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(entity.body).contains(productName)
    }
}