rootProject.name = "Personal Life OS"
include(":backend", ":desktop-client")

// —— 1) where to fetch Gradle plugins (for alias(libs.plugins.…))
pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
}
