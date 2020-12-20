package cabanas.garcia.ismael.storeroom.infrastructure.framework.repository.storeroom.jpa

import cabanas.garcia.ismael.storeroom.IntegrationTest
import cabanas.garcia.ismael.storeroom.domain.storeroom.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.context.jdbc.Sql.ExecutionPhase
import org.springframework.test.jdbc.JdbcTestUtils.countRowsInTableWhere
import java.util.stream.Stream

internal class JpaStoreroomRepositoryShould : IntegrationTest() {

    @Autowired
    @Qualifier("jpaStoreroomRepository")
    private lateinit var repository: StoreroomRepository

    @Autowired
    private lateinit var jdbcTemplate: JdbcTemplate

    @BeforeEach
    @Sql(
            executionPhase = ExecutionPhase.AFTER_TEST_METHOD,
            statements = ["DELETE FROM STOREROOM", "DELETE FROM STOREROOM_PRODUCT"]
    )
    internal fun `configure system under test`() {

    }

    @ParameterizedTest(name = "Given storeroom \"{0}\", when save it, then is persisted on DB")
    @MethodSource("storerooms")
    fun `save a storeroom on database`(aStoreroom: Storeroom) {
        repository.save(aStoreroom)

        assertThatStoreroomWasSaved(aStoreroom)
    }

    @Test
    @Sql(
            statements = [
                "INSERT INTO STOREROOM(ID, NAME, OWNER_ID) VALUES ('$SOME_STOREROOM_ID', '$SOME_NAME', '$SOME_OWNER_ID')",
                "INSERT INTO STOREROOM_PRODUCT(ID, STOREROOM_ID, STOCK) VALUES ('$SOME_PRODUCT_ID', '$SOME_STOREROOM_ID', '$SOME_STOCK')"
            ]
    )
    fun `find a storeroom from database`() {
        val storeroom = repository.findById(SOME_STOREROOM_ID)!!

        assertThat(storeroom.name).isEqualTo(SOME_NAME)
        assertThat(storeroom.products()).isNotEmpty
    }

    private companion object {
        private const val SOME_STOREROOM_ID = "a8ef97c6-2bdc-4867-b527-9ba3a1d02f80"
        private const val SOME_NAME = "some storeroom name"
        private const val SOME_OWNER_ID = "028f5812-3a79-45a8-b534-f1b7540a9092"
        private const val SOME_PRODUCT_ID = "1f87d7a9-9968-4ccb-8810-cf86566f6467"
        private const val SOME_STOCK = 10

        @JvmStatic
        fun storerooms() = Stream.of(
                Arguments.of(StoreroomMother.emptyStoreroom()),
                Arguments.of(StoreroomMother.aStoreroomWithProducts())
        )
    }

    private fun assertThatStoreroomWasSaved(expected: Storeroom) {
        assertThat(
                countRowsInTableWhere(
                        jdbcTemplate,
                        "STOREROOM",
                        "ID = '${expected.id.value}'"
                                + " AND OWNER_ID = '${expected.ownerId.value}'"
                                + " AND NAME = '${expected.name}'"
                )
        ).isEqualTo(1)

        if (expected.products().isNotEmpty()) {
            expected.products().forEach {
                assertThat(
                        countRowsInTableWhere(
                                jdbcTemplate,
                                "STOREROOM_PRODUCT",
                                "ID = '${it.id.value}'"
                                        + " AND STOREROOM_ID = '${expected.id.value}'"
                                        + " AND STOCK = ${it.stock.value}"
                        )
                ).isEqualTo(1)
            }
        }
    }
}