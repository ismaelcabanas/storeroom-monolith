package cabanas.garcia.ismael.storeroom.domain.api

import cabanas.garcia.ismael.storeroom.domain.product.Product
import cabanas.garcia.ismael.storeroom.domain.product.ProductDetails
import cabanas.garcia.ismael.storeroom.domain.product.UserId

@FunctionalInterface
interface CreateProduct {
    fun byUserWithDetails(userId: UserId, productDetails: ProductDetails): Product
}