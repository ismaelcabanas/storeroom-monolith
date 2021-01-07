package cabanas.garcia.ismael.storeroom.infrastructure.framework.configuration

import cabanas.garcia.ismael.storeroom.domain.shared.eventbus.InMemoryEventBus
import cabanas.garcia.ismael.storeroom.infrastructure.shared.bus.spring.SpringEventBus
import org.springframework.context.ApplicationEventPublisher
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class EventBusConfiguration {
    @Bean
    fun eventBus() = InMemoryEventBus()

    @Bean("springEventBus")
    fun springEventBus(applicationEventPublisher: ApplicationEventPublisher) = SpringEventBus(applicationEventPublisher)
}