package cabanas.garcia.ismael.storeroom.domain.storeroom

import com.github.javafaker.Faker

object ProductNameMother {
    fun random(): String =
        Faker.instance().company().name()

}