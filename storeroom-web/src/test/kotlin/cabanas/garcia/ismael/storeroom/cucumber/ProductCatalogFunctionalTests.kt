package cabanas.garcia.ismael.storeroom.cucumber

import cabanas.garcia.ismael.storeroom.cucumber.di.ProductCatalogPicoDependencyInjector
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