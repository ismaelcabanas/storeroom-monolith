package cabanas.garcia.ismael.storeroom.infrastructure.framework.controller.v1.request

import com.fasterxml.jackson.annotation.JsonProperty

data class CreateProductRequest (
        @JsonProperty("productId") val productId: String,
        @JsonProperty("productName") val productName: String
)

