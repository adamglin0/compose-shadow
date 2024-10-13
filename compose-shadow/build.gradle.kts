@file:OptIn(ExperimentalEncodingApi::class, ExperimentalWasmDsl::class)

import com.vanniktech.maven.publish.SonatypeHost
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import kotlin.io.encoding.ExperimentalEncodingApi

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.library)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.dokka)
    alias(libs.plugins.mavenPublish)
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    jvm("desktop")

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    macosX64()
    macosArm64()

    wasmJs()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.ui)
                implementation(libs.androidx.annotation)
            }
        }
        val skikoMain by creating {
            dependsOn(commonMain)
        }
        nativeMain.configure { dependsOn(skikoMain) }
        val desktopMain by getting {
            dependsOn(skikoMain)
        }
        val wasmJsMain by getting {
            dependsOn(skikoMain)
        }

    }
}

android {
    namespace = "com.adamglin.composeshadow"
    compileSdk = libs.versions.androidCompileSdk.get().toInt()
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")

    defaultConfig {
        minSdk = libs.versions.androidMinSdk.get().toInt()
        lint.targetSdk = libs.versions.androidTargetSdk.get().toInt()
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    buildFeatures {
        compose = true
    }
}


mavenPublishing {
    coordinates(
        groupId = "com.adamglin",
        artifactId = "compose-shadow",
        version = "1.0.0"
    )
    pom {
        name.set("compose-shadow")
        description.set("a kotlin platform library for show drop shadow in compose.")
        url.set("https://github.com/adamglin0/compose-shadow")
        licenses {
            license {
                name.set("MIT")
                url.set("https://opensource.org/licenses/MIT")
            }
        }
        developers {
            developer {
                name.set("adamglin")
                email.set("dev@adamglin.com")
            }
        }
        issueManagement {
            system.set("Github")
            url.set("https://github.com/adamglin0/compose-shadow/issues")
        }
        scm {
            connection.set("https://github.com/adamglin0/compose-shadow.git")
            url.set("https://github.com/adamglin0/compose-shadow")
        }
    }
    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)
    signAllPublications()
}

