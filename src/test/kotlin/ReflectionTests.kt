import id.walt.servicematrix.BaseService
import id.walt.servicematrix.ServiceProvider
import id.walt.servicematrix.ServiceRegistry
import id.walt.servicematrix.utils.ReflectionUtils.getKClassByName
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance

class ReflectionTests : StringSpec({
    "Reflection registration" {
        @Suppress("UNCHECKED_CAST")
        val instance: KClass<out BaseService> = getKClassByName("ReflectionTestService") as KClass<out BaseService>
        val service1 = getKClassByName("ReflectionTestServiceImpl1").createInstance() as BaseService
        ServiceRegistry.registerService(service1, instance)

        val myService = ReflectionTestService.getService()
        myService.function1() shouldBe 1

        val service2 = getKClassByName("ReflectionTestServiceImpl2").createInstance() as BaseService
        ServiceRegistry.registerService(service2, instance)

        myService.function1() shouldBe 2
    }
})

abstract class ReflectionTestService : BaseService() {
    override val implementation get() = serviceImplementation<ReflectionTestService>()

    open fun function1(): Int = implementation.function1()
    open fun function2(): String = implementation.function2()

    companion object : ServiceProvider {
        override fun getService() = object : ReflectionTestService() {}
    }
}

class ReflectionTestServiceImpl1 : ReflectionTestService() {
    override fun function1() = 1
    override fun function2() = "Impl 1"
}

class ReflectionTestServiceImpl2 : ReflectionTestService() {
    override fun function1() = 2
    override fun function2() = "Impl 2"
}
