package id.walt.servicematrix.utils

/**
 * General reflection utilities used by waltid-servicematrix
 */
object ReflectionUtils {
    /**
     * @return KClass of the specified class by full name
     */
    fun getKClassByName(name: String) = Class.forName(name).kotlin
}
