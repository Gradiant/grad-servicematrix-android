plugins {
    kotlin("jvm") version "1.5.10"
    `maven-publish`
    java
}

group = "id.walt.servicematrix"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()

    maven {
        setUrl("https://maven.pkg.jetbrains.space/walt-id/p/servicematrix/maven")
        credentials {
            username = "$username"
            password = "$password"
        }
    }
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.5.10")
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "id.walt.servicematrix"
            artifactId = "servicematrix"
            version = "1.0"

            from(components["java"])
        }
    }
    repositories {
        maven {
            setUrl("https://maven.pkg.jetbrains.space/walt-id/p/servicematrix/maven")
            credentials {
                username = "$username"
                password = "$password"
            }
        }
    }
}
