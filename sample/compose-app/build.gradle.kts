import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget


plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.applcation)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.compose.compiler)
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    jvm("desktop")

    listOf(
        iosArm64(),
        iosX64(),
        iosSimulatorArm64(),
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "sharedApp"
            isStatic = true
        }
    }

    js(IR) {
        moduleName = "sharedApp"
        browser {
            commonWebpackConfig {
                outputFileName = "sharedApp.js"
            }
        }
        binaries.executable()
    }

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        moduleName = "sharedApp"
        browser {
            commonWebpackConfig {
                outputFileName = "sharedApp.js"
            }
        }
        binaries.executable()
    }

    sourceSets {
        val desktopMain by getting
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.ui)
            implementation(compose.uiUtil)
            implementation(compose.material3)
            implementation(compose.components.uiToolingPreview)
            implementation(project(":compose-shadow"))
            implementation(libs.colorPicker)
        }

        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
        }
    }
}

android {
    namespace = "com.adamglin.composeshadow"
    compileSdk = libs.versions.androidCompileSdk.get().toInt()
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")
    buildFeatures {
        buildConfig = false
        compose = true
    }
    defaultConfig {
        minSdk = libs.versions.androidMinSdk.get().toInt()
        lint.targetSdk = libs.versions.androidTargetSdk.get().toInt()
        applicationId = "com.adamglin.composeshadow"
        versionCode = 1
        versionName = "0.0.1"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        vectorDrawables {
            useSupportLibrary = true
        }
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

    dependencies {
        implementation(libs.androidx.appcompat)
        implementation(libs.androidx.activity.compose)
        debugImplementation(compose.uiTooling)
    }
}


compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "org.adamglin.composeshadow"
            packageVersion = "1.0.0"
        }
    }
}

tasks.named("jsProductionExecutableCompileSync") {
    mustRunAfter("wasmJsBrowserProductionWebpack")
    mustRunAfter("wasmJsBrowserDistribution")
}