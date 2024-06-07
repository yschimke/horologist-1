/*
 * Copyright 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

plugins {
    id("com.android.application")
    kotlin("android")
    id("com.google.devtools.ksp")
    id("dagger.hilt.android.plugin")
    kotlin("plugin.serialization")
    kotlin("plugin.compose")
}

android {
    compileSdk = 34

    defaultConfig {
        applicationId = "com.google.android.horologist.ai.sample"

        minSdk = 21
        targetSdk = 34

        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        debug {
        }

        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )

            signingConfig = signingConfigs.getByName("debug")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures {
        compose = true
    }

    kotlinOptions {
        jvmTarget = "11"

        // Allow for widescale experimental APIs in Alpha libraries we build upon
        freeCompilerArgs = freeCompilerArgs +
            listOf(
                "-opt-in=com.google.android.horologist.annotations.ExperimentalHorologistApi",
            )
    }

    composeOptions {
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    lint {
        // https://buganizer.corp.google.com/issues/328279054
        disable.add("UnsafeOptInUsageError")
    }

    namespace = "com.google.android.horologist.ai.sample"
}

dependencies {
    api(projects.annotations)

    implementation(projects.ai.sample.core)
    implementation(projects.datalayer.core)
    implementation(projects.datalayer.phone)
    implementation(projects.datalayer.grpc)

    implementation(libs.dagger.hiltandroid)
    ksp(libs.dagger.hiltandroidcompiler)
    implementation(libs.hilt.navigationcompose)
    implementation(libs.androidx.navigation.compose)

    implementation(projects.datalayer.core)
    implementation(projects.datalayer.grpc)
    implementation(projects.datalayer.phone)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.kotlinx.coroutines.playservices)

    implementation(libs.androidx.corektx)
    implementation(libs.androidx.lifecycle.runtime)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.compose.bom))
    implementation(libs.androidx.activity.compose)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.graphics)
    implementation(libs.compose.ui.toolingpreview)
    implementation(libs.compose.material3)
    implementation(libs.playservices.wearable)
    implementation(libs.androidx.lifecycle.service)

    implementation(libs.kotlinx.serialization.core)

    testImplementation(libs.junit)
    testImplementation(libs.robolectric)

    debugImplementation(libs.compose.ui.tooling)
    debugImplementation(libs.compose.ui.test.manifest)

    androidTestImplementation(libs.androidx.test.runner)
}
