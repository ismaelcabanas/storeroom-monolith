package cabanas.garcia.ismael.storeroom.infrastructure.framework.repository.shoppinglist.jpa

import cabanas.garcia.ismael.storeroom.IntegrationTest
import cabanas.garcia.ismael.storeroom.domain.shoppinglist.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.jdbc.JdbcTestUtils

class JpaShoppingListRepositoryShould: IntegrationTest() {

    @Autowired
    @Qualifier("jpaShoppingListRepository")
    private lateinit var repository: JpaShoppingListRepository

    @Autowired
    private lateinit var jdbcTemplate: JdbcTemplate

    @Test
    @Sql(
            statements = [
                "DELETE FROM SHOPPING_LIST",
                "DELETE FROM SHOPPING_LIST_PRODUCTS",
                "INSERT INTO SHOPPING_LIST(ID, STOREROOM_ID, OWNER_ID) VALUES ('${SOME_SHOPPING_LIST_ID}', '${SOME_STOREROOM_ID}', '${SOME_OWNER_ID}')"
            ]
    )
    fun `find a shopping list from database`() {
        val shoppingList = repository.findBy(ShoppingListId(SOME_SHOPPING_LIST_ID))!!

        assertThat(shoppingList.storeroomId.value).isEqualTo(SOME_STOREROOM_ID)
        assertThat(shoppingList.ownerId.value).isEqualTo(SOME_OWNER_ID)
    }

    @Test
    @Sql(
            statements = [
                "DELETE FROM SHOPPING_LIST",
                "DELETE FROM SHOPPING_LIST_PRODUCTS"
            ]
    )
    fun `return null when a shopping list does not exist in database`() {
        val shoppingList = repository.findBy(ShoppingListId(SOME_SHOPPING_LIST_ID))

        assertThat(shoppingList).isNull()
    }

    @Test
    @Sql(
            statements = [
                "DELETE FROM SHOPPING_LIST",
                "DELETE FROM SHOPPING_LIST_PRODUCTS",
                "INSERT INTO SHOPPING_LIST(ID, STOREROOM_ID, OWNER_ID) VALUES ('${SOME_SHOPPING_LIST_ID}', '${SOME_STOREROOM_ID}', '${SOME_OWNER_ID}')"
            ]
    )
    fun `find a shopping list by storeroom from database`() {
        val shoppingList = repository.findBy(StoreroomId(SOME_STOREROOM_ID))!!

        assertThat(shoppingList.id.value).isEqualTo(SOME_SHOPPING_LIST_ID)
        assertThat(shoppingList.ownerId.value).isEqualTo(SOME_OWNER_ID)
    }

    @Test
    @Sql(
            statements = [
                "DELETE FROM SHOPPING_LIST",
                "DELETE FROM SHOPPING_LIST_PRODUCTS"
            ]
    )
    fun `return null when find a shopping list by storeroom that does not exist in database`() {
        val shoppingList = repository.findBy(StoreroomId(SOME_STOREROOM_ID))

        assertThat(shoppingList).isNull()
    }

    @Test
    @Sql(
            statements = [
                "DELETE FROM SHOPPING_LIST",
                "DELETE FROM SHOPPING_LIST_PRODUCTS"
            ]
    )
    fun `save a shopping list in database`() {
        val shoppingList = ShoppingList(SOME_SHOPPING_LIST_ID, SOME_STOREROOM_ID, SOME_OWNER_ID)

        repository.save(shoppingList)

        assertThatShoppingListWasSaved()
    }

    @Test
    @Sql(
            statements = [
                "DELETE FROM SHOPPING_LIST",
                "DELETE FROM SHOPPING_LIST_PRODUCTS",
                "INSERT INTO SHOPPING_LIST(ID, STOREROOM_ID, OWNER_ID) VALUES ('${SOME_SHOPPING_LIST_ID}', '${SOME_STOREROOM_ID}', '${SOME_OWNER_ID}')"
            ]
    )
    fun `save a shopping list with products in database`() {
        val shoppingList = ShoppingList(SOME_SHOPPING_LIST_ID, SOME_STOREROOM_ID, SOME_OWNER_ID)
        val shoppingListWithProducts = shoppingList.addProduct(Product(ProductId(SOME_PRODUCT_ID), SOME_PRODUCT_NAME))

        repository.save(shoppingListWithProducts)

        assertThatShoppingListWasSavedWithProducts()
    }

    private fun assertThatShoppingListWasSaved() {
        assertThat(
                JdbcTestUtils.countRowsInTableWhere(
                        jdbcTemplate,
                        "SHOPPING_LIST",
                        "ID = '${SOME_SHOPPING_LIST_ID}'"
                                + " AND STOREROOM_ID = '${SOME_STOREROOM_ID}'"
                                + " AND OWNER_ID = '${SOME_OWNER_ID}'"
                )
        ).isEqualTo(1)
    }

    private fun assertThatShoppingListWasSavedWithProducts() {
        assertThat(
                JdbcTestUtils.countRowsInTableWhere(
                        jdbcTemplate,
                        "SHOPPING_LIST_PRODUCTS",
                        "SHOPPING_LIST_ID = '${SOME_SHOPPING_LIST_ID}'"
                                + " AND ID = '${SOME_PRODUCT_ID}'"
                )
        ).isEqualTo(1)
    }

    companion object {
        private const val SOME_STOREROOM_ID = "a8ef97c6-2bdc-4867-b527-9ba3a1d02f80"
        private const val SOME_OWNER_ID = "028f5812-3a79-45a8-b534-f1b7540a9092"
        private const val SOME_SHOPPING_LIST_ID = "1f87d7a9-9968-4ccb-8810-cf86566f6467"
        private const val SOME_PRODUCT_ID = "3a74b1df-ad65-4179-b756-27c40ee5b02d"
        private const val SOME_PRODUCT_NAME = "some product name"
    }
}