package cabanas.garcia.ismael.storeroom.infrastructure.framework.repository.productcatalog.jpa.entity

import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "PRODUCT")
class ProductEntity(
        @Id var id: UUID,
        @Column var creatorId: UUID,
        @Column var name: String
)