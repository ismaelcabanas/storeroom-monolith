package cabanas.garcia.ismael.storeroom.domain.storeroom

import cabanas.garcia.ismael.storeroom.domain.storeroom.exception.ConsumeProductStockExceededException
import cabanas.garcia.ismael.storeroom.domain.storeroom.exception.ProductDoesNotExitsException
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.catchThrowable
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
        assertThat(sut.products()).isEmpty()
    }

    @Test
    fun `add new products to storeroom`() {
        val sut = StoreroomMother.emptyStoreroom()

        val actual = sut.addProduct(SOME_PRODUCT_ID, SOME_OWNER_ID)

        assertThat(actual.stockOf(SOME_PRODUCT_ID)).isEqualTo(0)
    }

    @Test
    fun `add new products with stock to storeroom`() {
        val sut = StoreroomMother.emptyStoreroom()

        val actual = sut.addProduct(SOME_PRODUCT_ID, SOME_OWNER_ID, SOME_STOCK)

        assertThat(actual.stockOf(SOME_PRODUCT_ID)).isEqualTo(SOME_STOCK)
    }

    @Test
    fun `add new stock to existent products to storeroom`() {
        val sut = StoreroomMother.aStoreroomWithProducts(setOf(Product(ProductId(SOME_PRODUCT_ID), Stock(3))))
        val newStock = 5

        val actual = sut.addProduct(SOME_PRODUCT_ID, SOME_OWNER_ID, newStock)

        assertThat(actual.stockOf(SOME_PRODUCT_ID)).isEqualTo(8)
    }

    @Test
    fun `consume stock from existent product in storeroom`() {
        val sut = StoreroomMother.aStoreroomWithProducts(setOf(Product(ProductId(SOME_PRODUCT_ID), Stock(3))))
        val consumedStock = 2

        val actual = sut.consumeProduct(SOME_PRODUCT_ID, SOME_OWNER_ID, consumedStock)

        assertThat(actual.stockOf(SOME_PRODUCT_ID)).isEqualTo(1)
    }

    @Test
    fun `throw product does not exist error when consume stock from product that does not exist in storeroom`() {
        val sut = StoreroomMother.emptyStoreroom()
        val consumedStock = 2

        val throwable = catchThrowable { sut.consumeProduct(SOME_PRODUCT_ID, SOME_OWNER_ID, consumedStock) }

        assertThat(throwable).isInstanceOf(ProductDoesNotExitsException::class.java)
        assertThat(throwable.message).isEqualTo("Product '$SOME_PRODUCT_ID' is not in the storeroom")
    }

    @Test
    fun `throw consume product exceeded error when product stock in storeroom is less than stock to consume`() {
        val sut = StoreroomMother.aStoreroomWithProducts(setOf(Product(ProductId(SOME_PRODUCT_ID), Stock(3))))
        val consumedStock = 4

        val throwable = catchThrowable { sut.consumeProduct(SOME_PRODUCT_ID, SOME_OWNER_ID, consumedStock) }

        assertThat(throwable).isInstanceOf(ConsumeProductStockExceededException::class.java)
        assertThat(throwable.message).isEqualTo("Product '$SOME_PRODUCT_ID' stock is 3 and you want consume 4 units of stock")
    }


}