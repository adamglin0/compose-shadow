pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "compose-shadow-project"
include(":compose-shadow")
// apps
rootDir.resolve("./sample").listFiles()?.forEach {
    if (it.isDirectory) {
        include(":sample:${it.name}")
    }
}