package cabanas.garcia.ismael.storeroom.domain.storeroom

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

private const val SOME_STOREROOM_ID: String = "some id"
private const val SOME_OWNER_ID:String = "some user id"
private const val SOME_STOREROOM_NAME:String = "some storeroom name"
private const val SOME_PRODUCT_ID:String = "some product id"
private const val SOME_STOCK:Int = 3

class StoreroomShould {

    @BeforeEach
    fun `setUp`() {

    }

    @Test
    fun `create a new storeroom for storing products`() {
        val sut = Storeroom.create(SOME_STOREROOM_ID, SOME_OWNER_ID, SOME_STOREROOM_NAME)

        assertThat(sut).isNotNull
        assertThat(sut.id.value).isEqualTo(SOME_STOREROOM_ID)
        assertThat(sut.ownerId.value).isEqualTo(SOME_OWNER_ID)
        assertThat(sut.name).isEqualTo(SOME_STOREROOM_NAME)
        assertThat(sut.products).isEmpty()
    }

    @Test
    fun `add new products to storeroom`() {
        val sut = Storeroom(StoreroomId(SOME_STOREROOM_ID), UserId(SOME_OWNER_ID), SOME_STOREROOM_NAME)

        val actual = sut.addProduct(SOME_PRODUCT_ID, SOME_OWNER_ID)

        assertThat(actual.stockOf(SOME_PRODUCT_ID)).isEqualTo(0)
    }

    @Test
    fun `add new products to storeroom with stock`() {
        val sut = Storeroom(StoreroomId(SOME_STOREROOM_ID), UserId(SOME_OWNER_ID), SOME_STOREROOM_NAME)

        val actual = sut.addProduct(SOME_PRODUCT_ID, SOME_OWNER_ID, SOME_STOCK)

        assertThat(actual.stockOf(SOME_PRODUCT_ID)).isEqualTo(SOME_STOCK)
    }

    @Test
    fun `add new stock to existent products to storeroom`() {
        val sut = givenStoreroomWithProductWithStock(SOME_STOCK)
        val newStock = 5

        val actual = sut.addProduct(SOME_PRODUCT_ID, SOME_OWNER_ID, newStock)

        assertThat(actual.stockOf(SOME_PRODUCT_ID)).isEqualTo(8)
    }

    private fun givenStoreroomWithProductWithStock(stock: Int): Storeroom {
        val storeroom = Storeroom(StoreroomId(SOME_STOREROOM_ID), UserId(SOME_OWNER_ID), SOME_STOREROOM_NAME)
        return storeroom.addProduct(SOME_PRODUCT_ID, SOME_OWNER_ID, stock)
    }

}