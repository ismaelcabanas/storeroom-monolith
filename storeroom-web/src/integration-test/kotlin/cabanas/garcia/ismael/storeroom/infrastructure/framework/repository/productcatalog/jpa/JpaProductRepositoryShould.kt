package cabanas.garcia.ismael.storeroom.infrastructure.framework.repository.productcatalog.jpa

import cabanas.garcia.ismael.storeroom.IntegrationTest
import cabanas.garcia.ismael.storeroom.domain.productcatalog.ProductMother
import cabanas.garcia.ismael.storeroom.domain.productcatalog.ProductRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.jdbc.JdbcTestUtils

internal class JpaProductRepositoryShould : IntegrationTest() {

    @Autowired
    private lateinit var jdbcTemplate: JdbcTemplate

    @Autowired
    private lateinit var repository: ProductRepository

    @BeforeEach
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, statements = ["DELETE FROM PRODUCT"])
    internal fun `configure system under test`() {

    }

    @Test
    fun `save product`() {
        val aProduct = ProductMother.aProduct()

        repository.save(aProduct)

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
