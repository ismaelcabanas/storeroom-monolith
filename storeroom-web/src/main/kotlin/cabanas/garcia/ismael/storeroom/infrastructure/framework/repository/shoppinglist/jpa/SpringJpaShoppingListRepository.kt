package cabanas.garcia.ismael.storeroom.infrastructure.framework.repository.shoppinglist.jpa

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface SpringJpaShoppingListRepository: JpaRepository<JpaShoppingList, UUID> {
    fun findByStoreroomId(storeroomId: UUID): JpaShoppingList?
}