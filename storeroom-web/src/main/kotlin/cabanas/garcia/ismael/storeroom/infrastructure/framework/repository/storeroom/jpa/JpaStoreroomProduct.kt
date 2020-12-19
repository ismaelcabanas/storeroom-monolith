package cabanas.garcia.ismael.storeroom.infrastructure.framework.repository.storeroom.jpa

import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "STOREROOM_PRODUCT")
data class JpaStoreroomProduct(
        @Id
        val id: UUID,
        @Column
        val storeroomId: UUID,
        @Column
        val stock: Int
)