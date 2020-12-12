package cabanas.garcia.ismael.storeroom.infrastructure.framework.repository.productcatalog.jpa

import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "PRODUCT")
class JpaProduct(
        @Id var id: UUID,
        @Column var creatorId: UUID,
        @Column var name: String
)