package cabanas.garcia.ismael.storeroom.domain.productcatalog

import cabanas.garcia.ismael.storeroom.domain.productcatalog.api.CreateProduct

class ProductCreator(private val productRepository: ProductRepository) : CreateProduct {

    override fun byUserWithDetails(userId: UserId, productDetails: ProductDetails): Product {
        if (existProductWithName(productDetails.name)) {
            throw ProductNameAlreadyExistsException(productDetails.name)
        }

        val product = Product(productDetails.id, userId, productDetails.name)

        return productRepository.save(product)
    }

    private fun existProductWithName(productName: String): Boolean {
        return productRepository.findByName(productName) != null
    }
}