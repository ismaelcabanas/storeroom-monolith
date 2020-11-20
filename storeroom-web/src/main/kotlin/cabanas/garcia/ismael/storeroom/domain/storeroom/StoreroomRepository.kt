package cabanas.garcia.ismael.storeroom.domain.storeroom

interface StoreroomRepository {
    fun findById(id: String): Storeroom
    fun save(storeroom: Storeroom)

}
