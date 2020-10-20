package cabanas.garcia.ismael.storeroom.infrastructure.framework.entity

import java.util.UUID
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class Product(
        @Id var id: UUID,
        var name: String
)