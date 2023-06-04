plugins {
    val kotlinVersion = "1.8.21"
    val androidVersion = "7.3.1"

    //trick: for the same plugin versions in all sub-modules
    id("com.android.application").version(androidVersion).apply(false)
    id("com.android.library").version(androidVersion).apply(false)
    kotlin("android").version(kotlinVersion).apply(false)
    kotlin("multiplatform").version(kotlinVersion).apply(false)
    kotlin("plugin.serialization").version(kotlinVersion).apply(false)
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
