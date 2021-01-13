package cabanas.garcia.ismael.storeroom.application.shoppinglist.addproduct

import cabanas.garcia.ismael.storeroom.application.shared.bus.command.CommandHandler
import cabanas.garcia.ismael.storeroom.domain.productcatalog.ProductRepository
import cabanas.garcia.ismael.storeroom.domain.shoppinglist.*
import cabanas.garcia.ismael.storeroom.domain.shoppinglist.exception.FindByStoreroomShoppingListDoesNotExist
import mu.KotlinLogging

private val logger = KotlinLogging.logger {}

class AddProductCommandHandler(
        private val shoppingListRepository: ShoppingListRepository,
        private val productRepository: ProductRepository
       ): CommandHandler<AddProductCommand> {

    override fun handle(command: AddProductCommand) {
        val shoppingList = shoppingListRepository.findBy(StoreroomId(command.storeroomId))
                ?: throw FindByStoreroomShoppingListDoesNotExist(StoreroomId(command.storeroomId))

        val product = productRepository.findById(command.productId)
                ?: throw ProductDoesNotExitsException(command.productId)

        val shoppingListUpdated = shoppingList.addProduct(Product(ProductId(product.id.value), product.name))

        shoppingListRepository.save(shoppingListUpdated)

        logger.info("Add product '${product.name}' to shopping list '${shoppingList.id}'")
    }
}
