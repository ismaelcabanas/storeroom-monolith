package cabanas.garcia.ismael.storeroom.domain.product

import cabanas.garcia.ismael.storeroom.domain.api.CreateProduct

class ProductCreator(private val productRepository: ProductRepository) : CreateProduct {

    override fun byUserWithDetails(userId: UserId, productDetails: ProductDetails): Product {
        productRepository.fetch(productDetails.id)?.let { throw ProductAlreadyExistsException(productDetails.id) }
        return productRepository.save(Product(productDetails.id, userId, productDetails.name))
    }
}