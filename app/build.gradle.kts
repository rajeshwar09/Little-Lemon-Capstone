plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    // Setup: Compose Compiler
    alias(libs.plugins.compose.compiler)
    // Setup: Serialization
    id("org.jetbrains.kotlin.plugin.serialization")
    // Setup: KSP
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.rs.littlelemonapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.rs.littlelemonapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        // Add to export scheme (make sure to delete and add "exportScheme = false in DAO file before delivery)
        ksp {
            arg("room.schemaLocation", "$projectDir/schemas")
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

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Setup: Navigation "androidx.navigation:navigation-compose:2.7.7"
    implementation(libs.androidx.navigation.compose)

    // Setup: LiveData "androidx.compose.runtime:runtime-livedata:1.6.8"
    implementation(libs.androidx.runtime.livedata)

    // Setup: Serialisation (Kotlin 2.0.0, "org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.1"
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.1")


    // Setup: Room using KSP "androidx.room:room-runtime:2.6.1" "androidx.room:room-compiler:2.6.1"
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)

    // Setup: ktor "io.ktor:ktor-serialization-kotlinx-json:2.3.0"
    // "io.ktor:ktor-client-android:2.3.12" "io.ktor:ktor-client-content-negotiation:2.3.0"
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(libs.ktor.client.android)
    implementation(libs.ktor.client.content.negotiation)

    // MenuItem "com.github.bumptech.glide:compose:1.0.0-alpha.1"
    implementation("com.github.bumptech.glide:compose:1.0.0-alpha.1")
}