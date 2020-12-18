package cabanas.garcia.ismael.storeroom.infrastructure.framework.repository.storeroom.jpa

import cabanas.garcia.ismael.storeroom.IntegrationTest
import cabanas.garcia.ismael.storeroom.domain.storeroom.StoreroomMother
import cabanas.garcia.ismael.storeroom.domain.storeroom.StoreroomRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.context.jdbc.Sql.ExecutionPhase
import org.springframework.test.jdbc.JdbcTestUtils.countRowsInTableWhere

internal class JpaStoreroomRepositoryShould : IntegrationTest() {

    @Autowired
    @Qualifier("jpaStoreroomRepository")
    private lateinit var repository: StoreroomRepository

    @Autowired
    private lateinit var jdbcTemplate: JdbcTemplate

    @BeforeEach
    @Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, statements = ["DELETE FROM STOREROOM"])
    internal fun `configure system under test`() {

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