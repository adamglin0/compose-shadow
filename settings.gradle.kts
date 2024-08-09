enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
    versionCatalogs {
        create("adamglin") {
            from(files("/Users/adamglin/libs.versions.toml"))
        }
    }
}

rootProject.name = "compose-shadow"
include(":drop-shadow")
// apps
rootDir.resolve("./sample").listFiles()?.forEach {
    if (it.isDirectory) {
        include(":sample:${it.name}")
    }
}