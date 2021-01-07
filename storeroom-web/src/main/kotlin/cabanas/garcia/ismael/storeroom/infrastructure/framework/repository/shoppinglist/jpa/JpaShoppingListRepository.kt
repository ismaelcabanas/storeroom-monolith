package cabanas.garcia.ismael.storeroom.infrastructure.framework.repository.shoppinglist.jpa

import cabanas.garcia.ismael.storeroom.domain.shoppinglist.*
import java.util.*

class JpaShoppingListRepository(private val jpaRepository: SpringJpaShoppingListRepository) : ShoppingListRepository {
    override fun findBy(id: ShoppingListId): ShoppingList? =
        jpaRepository.findById(UUID.fromString(id.value))
                .map { toDomain(it) }
                .orElse(null)

    override fun findBy(id: StoreroomId): ShoppingList? =
        jpaRepository.findByStoreroomId(UUID.fromString(id.value))
                ?.let { toDomain(it) }

    override fun save(shoppingList: ShoppingList) {
        val entity = toEntity(shoppingList)
        jpaRepository.save(entity)
    }

    private fun toEntity(shoppingList: ShoppingList): JpaShoppingList {
        val jpaShoppingList = JpaShoppingList(
                UUID.fromString(shoppingList.id.value),
                UUID.fromString(shoppingList.storeroomId.value),
                UUID.fromString(shoppingList.ownerId.value)
        )

        with(jpaShoppingList) {
            products = shoppingList.products()
                    .map { product ->
                        JpaShoppingListProduct(
                                UUID.fromString(product.id.value),
                                jpaShoppingList,
                                product.name
                        )
                    }
        }

        return jpaShoppingList
    }

    private fun toDomain(jpaEntity: JpaShoppingList): ShoppingList =
            ShoppingList(
                    ShoppingListId(jpaEntity.id.toString()),
                    StoreroomId(jpaEntity.storeroomId.toString()),
                    UserId(jpaEntity.ownerId.toString()),
                    jpaEntity.products.map { product -> Product(ProductId(product.id.toString()), product.name) }
            )

}