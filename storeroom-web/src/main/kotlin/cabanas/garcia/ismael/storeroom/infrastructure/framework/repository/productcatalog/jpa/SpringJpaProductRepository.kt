package cabanas.garcia.ismael.storeroom.infrastructure.framework.repository.productcatalog.jpa

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface SpringJpaProductRepository : JpaRepository<JpaProduct, UUID> {
    fun findByName(productName: String): JpaProduct?

}