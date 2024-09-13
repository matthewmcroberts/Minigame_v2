plugins {
    id("java")
}

group = "com.matthew.plugin"
version = "1.0-SNAPSHOT"

repositories {
}

dependencies {
}

tasks.test {
    useJUnitPlatform()
}