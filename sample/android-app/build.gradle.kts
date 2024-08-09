import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(adamglin.plugins.android.applcation)
    alias(adamglin.plugins.compose.compiler)
    alias(adamglin.plugins.kotlin.android)
}

android {
    namespace = "com.adamglin.composeshadow.android"
    compileSdk = adamglin.versions.androidCompileSdk.get().toInt()
    buildFeatures {
        buildConfig = false
        compose = true
    }
    defaultConfig {
        minSdk = adamglin.versions.androidMinSdk.get().toInt()
        lint.targetSdk = adamglin.versions.androidTargetSdk.get().toInt()
        applicationId = "com.adamglin.composeshadow"
        versionCode = 1
        versionName = "0.0.1"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = JvmTarget.JVM_11.target
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}
dependencies {
    implementation(adamglin.androidx.appcompat)
    implementation(adamglin.androidx.activity.compose)
    implementation(projects.sample.shared)
}
