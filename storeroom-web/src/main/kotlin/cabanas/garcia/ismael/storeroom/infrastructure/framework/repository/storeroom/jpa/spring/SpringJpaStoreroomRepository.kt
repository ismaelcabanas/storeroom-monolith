package cabanas.garcia.ismael.storeroom.infrastructure.framework.repository.storeroom.jpa.spring

import cabanas.garcia.ismael.storeroom.infrastructure.framework.repository.storeroom.jpa.entity.StoreroomEntity
import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface SpringJpaStoreroomRepository: CrudRepository<StoreroomEntity, UUID> {
}