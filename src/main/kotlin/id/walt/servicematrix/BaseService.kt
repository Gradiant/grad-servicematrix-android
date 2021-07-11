package id.walt.servicematrix

import com.sksamuel.hoplite.ConfigLoader
import java.io.File

interface ServiceConfiguration

abstract class BaseService {
    open val configuration: ServiceConfiguration? = null
    abstract val implementation: BaseService

    protected inline fun <reified T : ServiceConfiguration> fromConfiguration(configurationPath: String) =
        ConfigLoader().loadConfigOrThrow<T>(File(configurationPath))
}
