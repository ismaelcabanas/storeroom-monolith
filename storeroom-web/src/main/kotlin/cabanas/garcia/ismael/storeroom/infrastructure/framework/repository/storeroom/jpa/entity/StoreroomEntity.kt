package cabanas.garcia.ismael.storeroom.infrastructure.framework.repository.storeroom.jpa.entity

import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "STOREROOM")
class StoreroomEntity(
        @Id
        var id: UUID,
        @Column
        var ownerId: UUID,
        @Column
        var name: String)
