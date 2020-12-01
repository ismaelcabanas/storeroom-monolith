package cabanas.garcia.ismael.storeroom.cucumber

import cabanas.garcia.ismael.storeroom.cucumber.di.StoreroomPicoDependencyInjector
import io.cucumber.junit.Cucumber
import io.cucumber.junit.CucumberOptions
import org.junit.runner.RunWith

@RunWith(Cucumber::class)
@CucumberOptions(
        strict = true,
        plugin = ["pretty", "json:build/reports/tests/cucumber/storeroom.json"],
        features = ["classpath:features/storeroom/create-storeroom.feature"
                    //, "classpath:features/storeroom/replenish-product-to-storeroom.feature"
                   ],
        objectFactory = StoreroomPicoDependencyInjector::class)
class StoreroomFunctionalTests {
}