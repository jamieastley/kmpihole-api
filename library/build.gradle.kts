import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.vanniktech.mavenPublish)
    alias(libs.plugins.kotlinSerialization)
}

group = "dev.jamieastley"
version = "0.0.2"

kotlin {
    jvm()
    androidTarget {
        publishLibraryVariants("release")
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }
    iosX64()
    iosArm64()
    iosSimulatorArm64()
    linuxX64()

    sourceSets {
        commonMain.dependencies {
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.logging)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.serialization.kotlinx.json)
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.kotlinx.serialization.json)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
            implementation(libs.ktor.client.mock)
            implementation(libs.kotlinx.coroutines.test)
        }
        androidMain.dependencies {
            implementation(libs.ktor.client.okhttp)
            implementation(libs.kotlinx.coroutines.android)
        }
        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
        }
        jvmMain.dependencies {
            implementation(libs.ktor.client.okhttp)
        }
        linuxX64Main.dependencies {
            implementation(libs.ktor.client.curl)
        }
    }
}

android {
    namespace = "dev.jamieastley.kmpihole_api"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}
dependencies {
    implementation("io.ktor:ktor-client-logging:3.2.2")
}

mavenPublishing {
    publishToMavenCentral()

    signAllPublications()

    coordinates(group.toString(), "kmpihole-api", version.toString())

    pom {
        name = "KMPiHole API"
        description = "Multiplatform library for interacting with Pi-hole's API."
        inceptionYear = "2025"
        url = "https://github.com/jamieastley/kmpihole-api"
        licenses {
            license {
                name = "The Apache License, Version 2.0"
                url = "https://www.apache.org/licenses/LICENSE-2.0.txt"
                distribution = "https://www.apache.org/licenses/LICENSE-2.0.txt"
            }
        }
        developers {
            developer {
                id = "jamieastley"
                name = "Jamie Astley"
                url = "https://github.com/jamieastley"
            }
        }
        scm {
            url = "https://github.com/jamieastley/kmpihole-api"
            connection = "scm:git:git://github.com/jamieastley/kmpihole-api.git"
            developerConnection = "scm:git:ssh://git@github.com/jamieastley/kmpihole-api.git"
        }
    }
}
