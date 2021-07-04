plugins {
    kotlin("jvm") version "1.5.10"
    `maven-publish`
}

group = "id.walt.servicematrix"
version = "1.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.5.10")
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            pom {
                name.set("Walt.ID Service-Matrix")
                description.set("Kotlin/Java library for service registration.")
                url.set("https://walt.id")
            }
            from(components["java"])
        }
    }

    repositories {
        maven {
            url = uri("https://maven.letstrust.io/repository/waltid/")

            credentials {
                username = "letstrust-build"
                password = "naidohTeiraG9ouzoo0"
            }
        }
    }
}
