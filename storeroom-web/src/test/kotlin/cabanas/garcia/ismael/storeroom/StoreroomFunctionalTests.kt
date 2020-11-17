package cabanas.garcia.ismael.storeroom

import cabanas.garcia.ismael.storeroom.di.StoreroomPicoDependencyInjector
import io.cucumber.junit.Cucumber
import io.cucumber.junit.CucumberOptions
import org.junit.runner.RunWith

@RunWith(Cucumber::class)
@CucumberOptions(
        strict = true,
        plugin = ["pretty", "json:build/reports/tests/cucumber/storeroom.json"],
        features = ["classpath:features/storeroom/create-storeroom.feature"],
        objectFactory = StoreroomPicoDependencyInjector::class)
class StoreroomFunctionalTests {
}