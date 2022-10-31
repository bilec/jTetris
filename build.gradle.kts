import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.10"
    application
}

group = "com.github.bilec"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val hoplite: String by project
val koin: String by project

dependencies {
    implementation ("com.sksamuel.hoplite:hoplite-core:$hoplite")
    implementation ("com.sksamuel.hoplite:hoplite-hocon:$hoplite")

    implementation ("io.insert-koin:koin-core:$koin")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
}

application {
    mainClass.set("MainKt")
}