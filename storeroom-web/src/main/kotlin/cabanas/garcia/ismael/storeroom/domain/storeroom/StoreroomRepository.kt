package cabanas.garcia.ismael.storeroom.domain.storeroom

interface StoreroomRepository {
    fun findBy(id: StoreroomId): Storeroom?
    fun save(storeroom: Storeroom)

}
