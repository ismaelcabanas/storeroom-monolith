package cabanas.garcia.ismael.storeroom.application.productcatalog.createproduct

import cabanas.garcia.ismael.storeroom.application.ApplicationError
import cabanas.garcia.ismael.storeroom.application.shared.bus.command.CommandHandler
import cabanas.garcia.ismael.storeroom.domain.productcatalog.ProductNameAlreadyExistsException
import cabanas.garcia.ismael.storeroom.domain.productcatalog.ProductDetails
import cabanas.garcia.ismael.storeroom.domain.productcatalog.ProductId
import cabanas.garcia.ismael.storeroom.domain.productcatalog.UserId
import cabanas.garcia.ismael.storeroom.domain.productcatalog.api.CreateProduct

class CreateProductCommandHandler(private val createProduct: CreateProduct): CommandHandler<CreateProductCommand> {
    override fun handle(command: CreateProductCommand) {
        try {
            createProduct.byUserWithDetails(UserId(command.creatorId), ProductDetails(ProductId(command.productId), command.productName))
        } catch (e: ProductNameAlreadyExistsException) {
            throw ApplicationError(e.message, e)
        }
    }
}