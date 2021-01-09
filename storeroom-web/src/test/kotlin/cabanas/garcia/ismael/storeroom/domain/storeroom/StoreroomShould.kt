package cabanas.garcia.ismael.storeroom.domain.storeroom

import cabanas.garcia.ismael.storeroom.domain.storeroom.event.ProductAdded
import cabanas.garcia.ismael.storeroom.domain.storeroom.event.ProductConsumed
import cabanas.garcia.ismael.storeroom.domain.storeroom.event.ProductSoldOut
import cabanas.garcia.ismael.storeroom.domain.storeroom.exception.ConsumeProductStockExceededException
import cabanas.garcia.ismael.storeroom.domain.storeroom.exception.ProductDoesNotExitsException
import cabanas.garcia.ismael.storeroom.domain.storeroom.spi.DefaultStoreroomFactory
import io.kotest.assertions.asClue
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

private const val SOME_STOREROOM_ID: String = "some id"
private const val SOME_OWNER_ID:String = "some user id"
private const val SOME_STOREROOM_NAME:String = "some storeroom name"
private const val SOME_PRODUCT_ID:String = "some product id"
private const val SOME_STOCK:Int = 3

class StoreroomShould {

    lateinit var factory: StoreroomFactory

    @BeforeEach
    fun `setUp`() {
        factory = DefaultStoreroomFactory()
    }

    @Test
    fun `create a new storeroom for storing products`() {
        val actualStoreroom = factory.create(SOME_STOREROOM_ID, SOME_OWNER_ID, SOME_STOREROOM_NAME)

        val expectedStoreroom = Storeroom(SOME_STOREROOM_ID, SOME_OWNER_ID, SOME_STOREROOM_NAME)
        actualStoreroom shouldBe expectedStoreroom
    }

    @Test
    fun `add new products to storeroom`() {
        val storeroom = StoreroomMother.emptyStoreroom(SOME_STOREROOM_ID, SOME_OWNER_ID, SOME_STOREROOM_NAME)

        val actualStoreroom = storeroom.addProduct(SOME_PRODUCT_ID, SOME_OWNER_ID)

        val expectedStoreroom = Storeroom(SOME_STOREROOM_ID, SOME_OWNER_ID, SOME_STOREROOM_NAME, listOf(Product(SOME_PRODUCT_ID, 0)))
        actualStoreroom.asClue {
            it shouldBe expectedStoreroom
            it.events() shouldBe listOf(ProductAdded(SOME_PRODUCT_ID, SOME_STOREROOM_ID, SOME_OWNER_ID, 0))
        }
    }

    @Test
    fun `add new products with stock to storeroom`() {
        val storeroom = StoreroomMother.emptyStoreroom()

        val actualStoreroom = storeroom.addProduct(SOME_PRODUCT_ID, storeroom.ownerId.value, SOME_STOCK)

        val expectedStoreroom = Storeroom(storeroom.id.value, storeroom.ownerId.value, storeroom.name, listOf(Product(SOME_PRODUCT_ID, SOME_STOCK)))
        actualStoreroom.asClue {
            it shouldBe expectedStoreroom
            it.events() shouldBe listOf(ProductAdded(SOME_PRODUCT_ID, storeroom.id.value, storeroom.ownerId.value, 3))
        }
    }

    @Test
    fun `add new stock to existent products to storeroom`() {
        val storeroom = StoreroomMother.aStoreroomWithProducts(listOf(Product(SOME_PRODUCT_ID, 3)))
        val newStock = 5

        val actualStoreroom = storeroom.addProduct(SOME_PRODUCT_ID, storeroom.ownerId.value, newStock)

        val expectedStoreroom = Storeroom(storeroom.id.value, storeroom.ownerId.value, storeroom.name, listOf(Product(SOME_PRODUCT_ID, 8)))
        actualStoreroom.asClue {
            it shouldBe expectedStoreroom
            it.events() shouldBe listOf(ProductAdded(SOME_PRODUCT_ID, storeroom.id.value, storeroom.ownerId.value, 5))
        }
    }

    @Test
    fun `consume stock from existent product in storeroom`() {
        val storeroom = StoreroomMother.aStoreroomWithProducts(listOf(Product(SOME_PRODUCT_ID, 3)))
        val consumedStock = 2

        val actualStoreroom = storeroom.consumeProduct(SOME_PRODUCT_ID, storeroom.ownerId.value, consumedStock)

        val expectedStoreroom = Storeroom(storeroom.id.value, storeroom.ownerId.value, storeroom.name, listOf(Product(SOME_PRODUCT_ID, 1)))
        actualStoreroom.asClue {
            it shouldBe expectedStoreroom
            it.events() shouldBe listOf(ProductConsumed(SOME_PRODUCT_ID, storeroom.id.value, storeroom.ownerId.value, 2))
        }
    }

    @Test
    fun `product sold out when consume stock from existent product in storeroom`() {
        val storeroom = StoreroomMother.aStoreroomWithProducts(listOf(Product(SOME_PRODUCT_ID, 3)))
        val consumedStock = 3

        val actualStoreroom = storeroom.consumeProduct(SOME_PRODUCT_ID, storeroom.ownerId.value, consumedStock)

        val expectedStoreroom = Storeroom(storeroom.id.value, storeroom.ownerId.value, storeroom.name, listOf(Product(SOME_PRODUCT_ID, 0)))
        actualStoreroom.asClue {
            it shouldBe expectedStoreroom
            it.events() shouldBe listOf(
                    ProductConsumed(SOME_PRODUCT_ID, storeroom.id.value, storeroom.ownerId.value, 3),
                    ProductSoldOut(SOME_PRODUCT_ID, storeroom.id.value, storeroom.ownerId.value)
            )
        }
    }

    @Test
    fun `throw product does not exist error when consume stock from product that does not exist in storeroom`() {
        val storeroom = StoreroomMother.emptyStoreroom(SOME_STOREROOM_ID, SOME_OWNER_ID, SOME_STOREROOM_NAME)
        val consumedStock = 2

        val exception = shouldThrow<ProductDoesNotExitsException> {
            storeroom.consumeProduct(SOME_PRODUCT_ID, SOME_OWNER_ID, consumedStock)
        }
        exception.message shouldBe "Product '$SOME_PRODUCT_ID' is not in the storeroom"
    }

    @Test
    fun `throw consume product exceeded error when product stock in storeroom is less than stock to consume`() {
        val sut = StoreroomMother.aStoreroomWithProducts(listOf(Product(SOME_PRODUCT_ID, 3)))
        val consumedStock = 4

        val exception = shouldThrow<ConsumeProductStockExceededException> {
            sut.consumeProduct(SOME_PRODUCT_ID, SOME_OWNER_ID, consumedStock)
        }
        exception.message shouldBe "Product '$SOME_PRODUCT_ID' stock is 3 and you want consume 4 units of stock"
    }


}