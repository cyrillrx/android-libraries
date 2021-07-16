plugins {
    id("com.android.library")
    kotlin("android")
}

val moduleName = "remote"
val moduleVersion = "0.0.2"
val moduleDesc = "Remote control library in java."
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

    implementation("com.google.protobuf:protobuf-java:3.10.0")
}
