package cabanas.garcia.ismael.storeroom.infrastructure.framework

import org.springframework.boot.Banner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = [
	"cabanas.garcia.ismael.storeroom.infrastructure.framework.configuration",
	"cabanas.garcia.ismael.storeroom.infrastructure.framework.controller",
	"cabanas.garcia.ismael.storeroom.infrastructure"]
)
class Application

fun main(args: Array<String>) {
	runApplication<Application>(*args) {
		setBannerMode(Banner.Mode.OFF)
	}
}
