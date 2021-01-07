package cabanas.garcia.ismael.storeroom.infrastructure.framework.repository.shoppinglist.jpa

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "SHOPPING_LIST_PRODUCTS")
data class JpaShoppingListProduct(
        @Id
        val id: UUID,
        @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
        @JoinColumn(name = "SHOPPING_LIST_ID")
        val shoppingList: JpaShoppingList,
        @Column(name = "NAME")
        val name: String
)