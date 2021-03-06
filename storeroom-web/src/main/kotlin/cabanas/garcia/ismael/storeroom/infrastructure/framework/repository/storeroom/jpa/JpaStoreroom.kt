package cabanas.garcia.ismael.storeroom.infrastructure.framework.repository.storeroom.jpa

import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "STOREROOM")
data class JpaStoreroom(
        @Id
        val id: UUID,
        @Column
        val ownerId: UUID,
        @Column
        val name: String)
