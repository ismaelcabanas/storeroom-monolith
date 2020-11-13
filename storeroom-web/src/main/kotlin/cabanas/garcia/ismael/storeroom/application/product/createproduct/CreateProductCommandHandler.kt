package cabanas.garcia.ismael.storeroom.application.product.createproduct

import cabanas.garcia.ismael.storeroom.application.ApplicationError
import cabanas.garcia.ismael.storeroom.application.CommandHandler
import cabanas.garcia.ismael.storeroom.domain.product.ProductAlreadyExistsException
import cabanas.garcia.ismael.storeroom.domain.product.ProductDetails
import cabanas.garcia.ismael.storeroom.domain.product.ProductId
import cabanas.garcia.ismael.storeroom.domain.product.UserId
import cabanas.garcia.ismael.storeroom.domain.product.api.CreateProduct

class CreateProductCommandHandler(private val createProduct: CreateProduct): CommandHandler<CreateProductCommand> {
    override fun handle(command: CreateProductCommand) {
        try {
            createProduct.byUserWithDetails(UserId(command.creatorId), ProductDetails(ProductId(command.productId), command.productName))
        } catch (e: ProductAlreadyExistsException) {
            throw ApplicationError(e.message, e)
        }
    }
}