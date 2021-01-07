package cabanas.garcia.ismael.storeroom.infrastructure.framework.repository.shoppinglist.jpa

import java.util.UUID
import javax.persistence.*

@Entity
@Table(name = "SHOPPING_LIST")
data class JpaShoppingList(
        @Id
        val id: UUID,
        @Column(name = "STOREROOM_ID")
        val storeroomId: UUID,
        @Column(name = "OWNER_ID")
        val ownerId: UUID,
        @OneToMany(mappedBy = "shoppingList", cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
        var products: List<JpaShoppingListProduct> = emptyList()
)