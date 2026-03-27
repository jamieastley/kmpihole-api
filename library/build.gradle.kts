plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.vanniktech.mavenPublish)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.androidKmpLibrary)
}

group = "dev.jamieastley"
version = "0.0.4"

kotlin {
    jvm()
    androidLibrary {
        compileSdk = libs.versions.android.compileSdk.get().toInt()
        minSdk = libs.versions.android.minSdk.get().toInt()
        namespace = "dev.jamieastley.kmpihole.api"
        experimentalProperties["android.experimental.kmp.enableAndroidResources"] = true
    }
    iosX64()
    iosArm64()
    iosSimulatorArm64()
    linuxX64()

    sourceSets {
        commonMain.dependencies {
            implementation(libs.ktor.client.core)
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.kotlinx.serialization.json)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
        androidMain.dependencies {
            implementation(libs.ktor.client.okhttp)
            implementation(libs.kotlinx.coroutines.android)
        }
        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
        }
    }
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
