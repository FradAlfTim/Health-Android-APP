plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    //add ksp plugin for room
    id("com.google.devtools.ksp")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.assignmentproject"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.assignmentproject"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    // for bottom navigation bar and icons
    implementation("androidx.compose.material:material:1.6.3")
    implementation("androidx.navigation:navigation-compose:2.7.7")
    //Add the ksp and Room and LiveData
    implementation("androidx.room:room-runtime:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")
    implementation("com.google.firebase:firebase-auth:23.0.0")
    ksp("androidx.room:room-compiler:2.6.1")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.1")
    implementation("androidx.compose.runtime:runtime-livedata:1.6.1")

    // Add the dependency for the Google Play services library (Google sign in)
    implementation("com.google.android.gms:play-services-auth:20.7.0")

    implementation("androidx.core:core-ktx:1.13.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.activity:activity-compose:1.9.0")
    implementation(platform("androidx.compose:compose-bom:2023.08.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    implementation("com.github.PhilJay:MPAndroidChart:v3.1.0")


    val workVersion = "2.9.0"
    implementation("androidx.work:work-runtime:${workVersion}")
    implementation("androidx.work:work-runtime-ktx:${workVersion}")
    implementation("androidx.work:work-multiprocess:${workVersion}")


    val moshi_version = "1.15.0"
    implementation("com.squareup.moshi:moshi:$moshi_version")
    implementation("com.squareup.moshi:moshi-kotlin:$moshi_version")
    implementation("com.squareup.moshi:moshi-kotlin-codegen:$moshi_version")
    ksp("com.squareup.moshi:moshi-kotlin-codegen:$moshi_version")
}