package cabanas.garcia.ismael.storeroom.infrastructure.framework.controller.v1

import cabanas.garcia.ismael.storeroom.infrastructure.framework.configuration.DomainConfiguration
import cabanas.garcia.ismael.storeroom.infrastructure.framework.configuration.InfrastructureConfiguration
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.context.annotation.Import
import org.springframework.core.io.Resource
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@ExtendWith(SpringExtension::class)
@WebMvcTest(value = [ProductController::class])
@Import(value = [DomainConfiguration::class, InfrastructureConfiguration::class])
@ActiveProfiles("stubs", "test")
internal class ProductControllerShould {

    @Autowired
    private lateinit var mvc: MockMvc

    @Value("classpath:payloads/create-product-request.json")
    private lateinit var createProductRequest: Resource

    private val productsPath = "v1/products"

    @Test
    fun `should create a product`() {
        val requestContent = readProductCreationRequest()
        val userId = "myself"
        this.mvc.perform(
                MockMvcRequestBuilders.post("/$productsPath")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .header("User-Id", userId)
                        .content(requestContent))
                .andExpect(MockMvcResultMatchers.status().isCreated)
                .andExpect(MockMvcResultMatchers.header().string("Location", "http://localhost/$productsPath/28fa98e9-0816-4fc6-9b31-d8ce757387f0"))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
                //.andDo(documentCreateProfile())

    }

    private fun readProductCreationRequest() = createProductRequest.file.readBytes()
}