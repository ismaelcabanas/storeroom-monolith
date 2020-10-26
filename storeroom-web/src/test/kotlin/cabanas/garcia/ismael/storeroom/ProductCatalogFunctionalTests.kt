package cabanas.garcia.ismael.storeroom

import io.cucumber.junit.Cucumber
import io.cucumber.junit.CucumberOptions
import org.junit.runner.RunWith

@RunWith(Cucumber::class)
@CucumberOptions(
        strict = true,
        plugin = ["pretty", "json:build/reports/tests/cucumber/recommendation.json"],
        features = ["classpath:features/adding-product-to-catalog.feature"],
        objectFactory = PicoDependencyInjector::class)
class ProductCatalogFunctionalTests {
}