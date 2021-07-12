package id.walt.servicematrix

import com.sksamuel.hoplite.ConfigLoader
import java.io.File

/**
 * Tag data classes with this interface to use it as configuration for your service
 */
interface ServiceConfiguration

/**
 * Base class that a service has to inherit from
 */
abstract class BaseService {
    /**
     * override using your custom ServiceConfiguration and get it through
     * `= fromConfiguration<YourCustomServiceConfig>(configPath)`
     * configPath should be taken from a primary constructor with a single string argument
     */
    open val configuration: ServiceConfiguration?
        get() = error("You have not defined a configuration for this service.")

    abstract val implementation: BaseService

    inline fun <reified Service : BaseService> serviceImplementation(): Service = ServiceRegistry.getService()

    protected inline fun <reified T : ServiceConfiguration> fromConfiguration(configurationPath: String) =
        ConfigLoader().loadConfigOrThrow<T>(File(configurationPath))
}
