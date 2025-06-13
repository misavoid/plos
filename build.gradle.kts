// root build.gradle.kts
plugins {
    kotlin("jvm")                           version "2.1.21"   apply false
    id("org.jetbrains.kotlin.plugin.compose") version "2.1.21" apply false  // <— new
    id("org.jetbrains.compose")             version "1.8.1"    apply false
}

subprojects {
    repositories {
        mavenCentral()
        google()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}
