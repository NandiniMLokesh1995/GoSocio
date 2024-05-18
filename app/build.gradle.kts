plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("kotlin-kapt")
    kotlin("plugin.serialization") version "1.9.23"
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.gosocio"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.gosocio"
        minSdk = 29
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.coil)
    implementation(libs.coroutines)
    implementation(libs.retrofit)
    implementation(libs.retrofitGson)
    implementation(libs.okhttp)
    implementation(libs.paging)
    annotationProcessor(libs.roomCompiler)
    implementation(libs.room)
    implementation(libs.roomRuntime)
    implementation(libs.room.paging)
    kapt(libs.room.kapt)
    implementation(libs.serialization)
    // Lifecycle components
    implementation(libs.androidx.lifecycle)
    implementation(libs.androidx.lifecycle.viewmodel)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)

    implementation( libs.androidx.activity.ktx)
}
kapt {
    correctErrorTypes = true
}