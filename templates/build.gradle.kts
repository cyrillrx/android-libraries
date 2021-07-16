plugins {
    id("com.android.library")
    kotlin("android")
}

val moduleName = "ui-templates"
val moduleVersion = "0.3.6"
val moduleDesc = "A basic library for Android containing UI components, and miscellaneous utils."
project.version = moduleVersion

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

    implementation(project(":core"))
    compileOnly(files("../libs/logger-1.6.1.aar"))

    implementation("androidx.appcompat:appcompat:1.3.0")
    implementation("androidx.recyclerview:recyclerview:1.2.1")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")

    testImplementation("junit:junit:4.13.2")

    androidTestImplementation("androidx.test:runner:1.4.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}
