package cabanas.garcia.ismael.storeroom

import cabanas.garcia.ismael.storeroom.di.ProductCatalogPicoDependencyInjector
import io.cucumber.junit.Cucumber
import io.cucumber.junit.CucumberOptions
import org.junit.runner.RunWith

@RunWith(Cucumber::class)
@CucumberOptions(
        strict = true,
        plugin = ["pretty", "json:build/reports/tests/cucumber/product.json"],
        features = ["classpath:features/productcatalog/add-product-to-catalog.feature"],
        objectFactory = ProductCatalogPicoDependencyInjector::class)
class ProductCatalogFunctionalTests {
}