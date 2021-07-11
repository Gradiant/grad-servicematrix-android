# ServiceMatrix, by WaltID

_Interchange service-implementation & service-configuration at runtime (using dynamic dependency injection), for Kotlin._  

All the services, the implementations, the configurations, etc. are typesafe, as all things should be.

Maintainer: @KBurgmann

## Quickstart
### Setup
Add the dependency using Maven or Gradle.

### Create your first service
```kotlin
abstract class SimpleTestService : BaseService() {
    override val implementation get() = ServiceRegistry.getService<SimpleTestService>()

    open fun function1(): Int = implementation.function1()
    open fun function2(): String = implementation.function2()

    companion object : ServiceProvider {
        override fun getService() = object : SimpleTestService() {}
    }
}
```

### Create your first service *implementation*
```kotlin
class SimpleTestServiceImpl1 : SimpleTestService() {
    override fun function1() = 1
    override fun function2() = "Impl 1"
}
```

#### Implementations can be inherited from implementations too!

```kotlin
class InheritedFromImpl1 : SimpleTestServiceImpl1() {
    override fun function2() = "The better ${super.function2()}"
}
```

### Inject them *at runtime!*
Using code:
```kotlin
ServiceRegistry.registerService<SimpleTestService>(SimpleTestServiceImpl1())
```

Or using a service-matrix file:
```
ServiceMatrixTestService=ServiceMatrixTestServiceImpl1
```
```kotlin
ServiceMatrix("service-matrix.properties")
```

### Want your implementation to have configuration too?
Your new service implementation:

```kotlin
class ConfigurationTestServiceImpl1(configurationPath: String) : ConfigurationTestService() {
    data class SomeExtraThings(val name: String)
    data class MyConfig1(val env: String, val someExtraThings: SomeExtraThings) : ServiceConfiguration

    override val configuration: MyConfig1 = fromConfiguration(configurationPath)

    override fun someInfoText(): String = configuration.someExtraThings.name
}
```

And the matching config file:
```hocon
env: "staging"
        
someExtraThings: {
    name: "Implementation Nr. 1"
}
```

### At-runtime reconfiguration

For the following service:
```kotlin
abstract class ReregistrationTestService : BaseService() {
    override val implementation get() = ServiceRegistry.getService<ReregistrationTestService>()

    open fun function1(): Int = implementation.function1()

    companion object : ServiceProvider {
        override fun getService() = object : ReregistrationTestService() {}
    }
}

class ReregistrationTestServiceImpl1 : ReregistrationTestService() {
    override fun function1() = 1
}

class ReregistrationTestServiceImpl2 : ReregistrationTestService() {
    override fun function1() = 2
}
```

The output will be:
```kotlin
// Register implementation 1
ServiceRegistry.registerService<ReregistrationTestService>(ReregistrationTestServiceImpl1())

val service = ReregistrationTestService.getService()

println(service.function1()) // 1

// Reregister with implementation 2
ServiceRegistry.registerService<ReregistrationTestService>(ReregistrationTestServiceImpl2())

// Still calling the same variable!
println(service.function1()) // 2
```
