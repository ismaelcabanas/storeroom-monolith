package cabanas.garcia.ismael.storeroom.cucumber.di

import cabanas.garcia.ismael.storeroom.application.productcatalog.createproduct.CreateProductCommandHandler
import cabanas.garcia.ismael.storeroom.domain.productcatalog.ProductCreator
import cabanas.garcia.ismael.storeroom.domain.productcatalog.spi.stubs.InMemoryProductRepository
import io.cucumber.core.backend.ObjectFactory
import io.cucumber.picocontainer.PicoFactory

class ProductCatalogPicoDependencyInjector : ObjectFactory {

    private val delegate: PicoFactory = PicoFactory()

    init {
        addClass(ProductCreator::class.java)
        addClass(InMemoryProductRepository::class.java)
        addClass(CreateProductCommandHandler::class.java)
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