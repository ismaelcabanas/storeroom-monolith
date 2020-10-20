package cabanas.garcia.ismael.storeroom.infrastructure.framework

import cabanas.garcia.ismael.storeroom.domain.ProductRepository
import cabanas.garcia.ismael.storeroom.infrastructure.framework.entity.Product
import com.ninjasquad.springmockk.MockkBean
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import java.util.*
import io.mockk.every
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post

@WebMvcTest
class ApiRestTests(@Autowired val mockMvc: MockMvc) {
    @MockkBean
    private lateinit var productRepository: ProductRepository

    @Test
    fun `List products`() {
        val canOfCorn = Product(UUID.randomUUID(), "Lata de maíz")
        val canOfPees = Product(UUID.randomUUID(), "Lata de guisantes")
        every { productRepository.findAll() } returns listOf(canOfCorn, canOfPees)
        mockMvc.perform(get("/api/v1/products/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("\$.[0].id").value(canOfCorn.id.toString()))
                .andExpect(jsonPath("\$.[0].name").value(canOfCorn.name))
                .andExpect(jsonPath("\$.[1].id").value(canOfPees.id.toString()))
                .andExpect(jsonPath("\$.[1].name").value(canOfPees.name))
    }
}