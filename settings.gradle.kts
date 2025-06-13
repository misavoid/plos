// settings.gradle.kts
enableFeaturePreview("VERSION_CATALOGS")

pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }

    // 1) Declare the same version-catalog here so that
    //    alias(libs.plugins.*) works in this block.
    versionCatalogs {
        create("libs") {
            from(files("gradle/libs.versions.toml"))
        }
    }

    // 2) Wire your plugin IDs → versions via aliases
    plugins {
        alias(libs.plugins.kotlinMultiplatform)
        alias(libs.plugins.composeCompiler)
        alias(libs.plugins.jetbrainsCompose)
    }
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }

    // 3) And declare it again here so libs is also
    //    available in your build.gradle(.kts) dependencies.
    versionCatalogs {
        create("libs") {
            from(files("gradle/libs.versions.toml"))
        }
    }
}

rootProject.name = "PersonalLifeOS"
include("shared", "desktop", "android")
