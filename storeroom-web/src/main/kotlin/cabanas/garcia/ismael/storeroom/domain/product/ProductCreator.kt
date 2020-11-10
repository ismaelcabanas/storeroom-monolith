package cabanas.garcia.ismael.storeroom.domain.product

import cabanas.garcia.ismael.storeroom.domain.product.api.CreateProduct

class ProductCreator(private val productRepository: ProductRepository) : CreateProduct {

    override fun byUserWithDetails(userId: UserId, productDetails: ProductDetails): Product {
        if (existProductWithName(productDetails.name)) {
            throw ProductAlreadyExistsException(productDetails.name)
        }

        val product = Product(productDetails.id, userId, productDetails.name)

        return productRepository.save(product)
    }

    private fun existProductWithName(productName: String): Boolean {
        return productRepository.findByName(productName) != null
    }
}