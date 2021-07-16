plugins {
    id("com.android.library")
    kotlin("android")
}

val moduleName = "core"
val moduleVersion = "0.5.1"
val moduleDesc = "A basic library for Android containing UI components, and miscellaneous utils."
version = moduleVersion

android {
    compileSdkVersion(Version.compileSdk)
    buildToolsVersion(Version.buildToolsVersion)

    defaultConfig {
        minSdkVersion(Version.minSdk)
        targetSdkVersion(Version.targetSdk)
        versionName(moduleVersion)
    }
}

dependencies {
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7")

    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.appcompat:appcompat:1.3.0")
    implementation("androidx.cardview:cardview:1.0.0")
    implementation("androidx.recyclerview:recyclerview:1.2.1")
    implementation("androidx.preference:preference:1.1.1")

    implementation("com.google.android.material:material:1.4.0")

    // External libraries
    implementation("com.google.code.gson:gson:2.8.6")

    testImplementation("org.jetbrains.kotlin:kotlin-stdlib")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
}
