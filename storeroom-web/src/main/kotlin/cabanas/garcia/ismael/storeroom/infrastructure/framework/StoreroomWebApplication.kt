package cabanas.garcia.ismael.storeroom.infrastructure.framework

import org.springframework.boot.Banner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = [
	"cabanas.garcia.ismael.storeroom.infrastructure.framework.configuration",
	"cabanas.garcia.ismael.storeroom.infrastructure"]
)
class StoreroomWebApplication

fun main(args: Array<String>) {
	runApplication<StoreroomWebApplication>(*args) {
		setBannerMode(Banner.Mode.OFF)
	}
}
