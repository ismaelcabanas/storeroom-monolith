package cabanas.garcia.ismael.storeroom.domain.productcatalog.api

import cabanas.garcia.ismael.storeroom.domain.productcatalog.Product
import cabanas.garcia.ismael.storeroom.domain.productcatalog.ProductDetails
import cabanas.garcia.ismael.storeroom.domain.productcatalog.UserId

@FunctionalInterface
interface CreateProduct {
    fun byUserWithDetails(userId: UserId, productDetails: ProductDetails): Product
}