package cabanas.garcia.ismael.storeroom.di

import cabanas.garcia.ismael.storeroom.application.storeroom.createstoreroom.CreateStoreroomCommandHandler
import cabanas.garcia.ismael.storeroom.domain.storeroom.spi.DefaultStoreroomFactory
import cabanas.garcia.ismael.storeroom.domain.storeroom.spi.InMemoryStoreroomRepository
import io.cucumber.core.backend.ObjectFactory
import io.cucumber.picocontainer.PicoFactory

class StoreroomPicoDependencyInjector : ObjectFactory {

    private val delegate: PicoFactory = PicoFactory()

    init {
        addClass(CreateStoreroomCommandHandler::class.java)
        addClass(InMemoryStoreroomRepository::class.java)
        addClass(DefaultStoreroomFactory::class.java)
    }

    override fun addClass(glueClass: Class<*>?): Boolean {
        return delegate.addClass(glueClass)
    }

    override fun <T : Any?> getInstance(glueClass: Class<T>?): T {
        return delegate.getInstance(glueClass)
    }

    override fun start() {
        delegate.start()
    }

    override fun stop() {
        delegate.stop()
    }
}