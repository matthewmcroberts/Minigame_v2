plugins {
    id("java")
    `java-library`
}

group = "com.matthew.plugin"
version = "1.0-SNAPSHOT"


subprojects {
    apply(plugin = "java-library")

    repositories {
        mavenCentral()
        maven("https://repo.papermc.io/repository/maven-public/")
    }

    dependencies {
        testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.2")
        testImplementation("org.junit.jupiter:junit-jupiter")
        testImplementation("org.mockito:mockito-core:5.11.0")

        compileOnly("org.projectlombok:lombok:1.18.30")

        annotationProcessor("org.projectlombok:lombok:1.18.30")
    }

    java {
        toolchain.languageVersion.set(JavaLanguageVersion.of(21))
    }

    tasks.test {
        useJUnitPlatform()
    }
}