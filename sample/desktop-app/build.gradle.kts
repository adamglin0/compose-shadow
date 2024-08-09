import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(adamglin.plugins.kotlin.jvm)
    alias(adamglin.plugins.compose.multiplatform)
    alias(adamglin.plugins.compose.compiler)
}

dependencies {
    implementation(compose.runtime)
    implementation(compose.foundation)
    implementation(compose.ui)
    implementation(compose.components.resources)
    implementation(compose.components.uiToolingPreview)
    implementation(compose.desktop.currentOs)

    implementation(projects.sample.shared)
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
