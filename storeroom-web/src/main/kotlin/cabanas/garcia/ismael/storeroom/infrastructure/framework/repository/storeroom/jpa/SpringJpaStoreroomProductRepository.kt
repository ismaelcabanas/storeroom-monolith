package cabanas.garcia.ismael.storeroom.infrastructure.framework.repository.storeroom.jpa

import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface SpringJpaStoreroomProductRepository : JpaRepository<JpaStoreroomProduct, UUID>{
    fun findByStoreroomId(storeroomId: UUID): List<JpaStoreroomProduct>
}