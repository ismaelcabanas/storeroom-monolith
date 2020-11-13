package cabanas.garcia.ismael.storeroom

import io.cucumber.junit.Cucumber
import io.cucumber.junit.CucumberOptions
import org.junit.runner.RunWith

@RunWith(Cucumber::class)
@CucumberOptions(
        strict = true,
        plugin = ["pretty", "json:build/reports/tests/cucumber/product.json"],
        features = ["classpath:features/add-product-to-catalog.feature"],
        objectFactory = PicoDependencyInjector::class)
class ProductCatalogFunctionalTests {
}