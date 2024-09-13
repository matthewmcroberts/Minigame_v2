plugins {
    id("java")
}

group = "com.matthew.plugin"
version = "1.0-SNAPSHOT"

val pluginDescription = "Minigame Plugin."

repositories {

}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.21.1-R0.1-SNAPSHOT")

    implementation(project(":minigame-common"))
    implementation(project(":minigame-api"))
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

tasks {
    test {
        useJUnitPlatform()
    }

    processResources {
        filesMatching("plugin.yml") {
            expand(
                "version" to version,
                "desc" to pluginDescription
            )
        }
    }
}
