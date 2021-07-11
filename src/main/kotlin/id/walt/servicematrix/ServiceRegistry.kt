package id.walt.servicematrix

import id.walt.servicematrix.exceptions.UnimplementedServiceException
import kotlin.reflect.KClass

object ServiceRegistry {
    val services = HashMap<KClass<out BaseService>, BaseService>()

    inline fun <reified T : BaseService> registerService(serviceImplementation: BaseService) {
        services[T::class] = serviceImplementation
    }

    fun registerService(serviceImplementation: BaseService, serviceType: KClass<out BaseService>) {
        services[serviceType] = serviceImplementation
    }

    inline fun <reified Service : BaseService> getService(): Service =
        (services[Service::class] ?: throw UnimplementedServiceException(Service::class.qualifiedName)) as Service
}
