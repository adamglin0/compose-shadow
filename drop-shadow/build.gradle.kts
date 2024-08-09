import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(adamglin.plugins.kotlin.multiplatform)
    alias(adamglin.plugins.android.library)
    alias(adamglin.plugins.compose.compiler)
    alias(adamglin.plugins.compose.multiplatform)
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

    sourceSets {
        val desktopMain by getting
        commonMain.dependencies {
            compileOnly(compose.runtime)
            compileOnly(compose.foundation)
            compileOnly(compose.ui)
        }
    }
}

android {
    namespace = "com.adamglin.composeshadow"
    compileSdk = adamglin.versions.androidCompileSdk.get().toInt()
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")

    defaultConfig {
        minSdk = adamglin.versions.androidMinSdk.get().toInt()
        lint.targetSdk = adamglin.versions.androidTargetSdk.get().toInt()
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