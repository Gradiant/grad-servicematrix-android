package id.walt.servicematrix

import id.walt.servicematrix.exceptions.UnimplementedServiceException
import kotlin.reflect.KClass

/**
 * Mapping of services and their respective service-implementations
 */
object ServiceRegistry {
    /**
     * Actual mapping happens here, you can directly access the map too.
     */
    val services = HashMap<KClass<out BaseService>, BaseService>()

    /**
     * Register a implementation for a specific service
     * Example: `registerService<MyCustomService>(MyCustomImplementation())`
     */
    inline fun <reified T : BaseService> registerService(serviceImplementation: BaseService) {
        services[T::class] = serviceImplementation
    }

    /**
     * Register a implementation for a specific service
     * Example: `registerService(MyCustomImplementation(), MyCustomService::class)`
     */
    fun registerService(serviceImplementation: BaseService, serviceType: KClass<out BaseService>) {
        services[serviceType] = serviceImplementation
    }

    /**
     * Get a service from the service registry
     * Example: `getService<MyCustomService>()`
     */
    inline fun <reified Service : BaseService> getService(): Service =
        (services[Service::class] ?: throw UnimplementedServiceException(Service::class.qualifiedName)) as Service
}
