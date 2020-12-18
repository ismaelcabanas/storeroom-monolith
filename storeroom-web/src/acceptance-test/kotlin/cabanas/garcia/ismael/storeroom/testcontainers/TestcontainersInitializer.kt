package cabanas.garcia.ismael.storeroom.testcontainers

import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.core.env.MapPropertySource
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.lifecycle.Startables
import java.time.ZoneOffset
import java.util.TimeZone
import java.util.stream.Stream

class TestcontainersInitializer : ApplicationContextInitializer<ConfigurableApplicationContext> {
  private val postgre = PostgreSQLContainer<Nothing>(DOCKER_IMAGE).apply {
    withDatabaseName("storeroom")
    withUrlParam("currentSchema", "storeroom")
    withUsername("postgres")
    withPassword("postgres")
    withReuse(true)
  }

  override fun initialize(applicationContext: ConfigurableApplicationContext?) {
    TimeZone.setDefault(TimeZone.getTimeZone(ZoneOffset.UTC))

    startContainers()

    if (applicationContext != null) {
      val environment = applicationContext.environment

      val testcontainers = MapPropertySource("testcontainers", createConnectionConfiguration())
      environment.propertySources.addFirst(testcontainers)
    }
  }

  private fun startContainers() {
    Startables.deepStart(Stream.of(postgre)).join()
    // we can add further containers
    // here like rabbitmq or other databases
  }

  private fun createConnectionConfiguration() =
          mapOf<String, String>(
            Pair("spring.datasource.url", postgre.jdbcUrl),
            Pair("spring.datasource.username", postgre.username),
            Pair("spring.datasource.password", postgre.password)
          )

  companion object {
    private const val DOCKER_IMAGE = "postgres:12.4"
  }
}
