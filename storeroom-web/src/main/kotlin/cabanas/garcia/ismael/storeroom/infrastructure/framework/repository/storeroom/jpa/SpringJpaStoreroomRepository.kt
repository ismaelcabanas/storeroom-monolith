package cabanas.garcia.ismael.storeroom.infrastructure.framework.repository.storeroom.jpa

import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface SpringJpaStoreroomRepository: JpaRepository<JpaStoreroom, UUID> {
}