package cabanas.garcia.ismael.storeroom.infrastructure.framework

import cabanas.garcia.ismael.storeroom.domain.productcatalog.ProductRepository
import cabanas.garcia.ismael.storeroom.infrastructure.framework.repository.productcatalog.jpa.JpaProduct
import com.ninjasquad.springmockk.MockkBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import java.util.*

@WebMvcTest
class ApiRestTests(@Autowired val mockMvc: MockMvc) {
    @MockkBean
    private lateinit var productRepository: ProductRepository

    //@Test
    fun `List products`() {
        val canOfCorn = JpaProduct(UUID.randomUUID(), UUID.randomUUID(),"Lata de maíz")
        val canOfPees = JpaProduct(UUID.randomUUID(), UUID.randomUUID(),"Lata de guisantes")
        //every { productRepository.findAll() } returns listOf(canOfCorn, canOfPees)
        mockMvc.perform(get("/api/v1/products/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("\$.[0].id").value(canOfCorn.id.toString()))
                .andExpect(jsonPath("\$.[0].name").value(canOfCorn.name))
                .andExpect(jsonPath("\$.[1].id").value(canOfPees.id.toString()))
                .andExpect(jsonPath("\$.[1].name").value(canOfPees.name))
    }
}