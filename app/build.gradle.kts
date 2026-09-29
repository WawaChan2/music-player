plugins {
  alias(libs.plugins.android.application)
  alias(libs.plugins.kotlin.compose)
  alias(libs.plugins.kotlin.serialization)
  alias(libs.plugins.devtools.ksp)
  alias(libs.plugins.hilt.android)
}

android {
  namespace = "com.wawa.musicplayer"
  compileSdk {
    version = release(37)
  }

  defaultConfig {
    applicationId = "com.wawa.musicplayer"
    minSdk = 24
    targetSdk = 37
    versionCode = 1
    versionName = "1.0"

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }

  buildTypes {
    release {
      optimization {
        enable = false
      }
    }
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
  }
  buildFeatures {
    compose = true
  }
}

dependencies {
  implementation(platform(libs.androidx.compose.bom))
  implementation(libs.androidx.activity.compose)
  implementation(libs.androidx.compose.material3)
  implementation(libs.androidx.compose.material3.window.size.class1)
  implementation(libs.androidx.compose.material3.adaptive.navigation.suite)
  implementation(libs.androidx.compose.ui)
  implementation(libs.androidx.compose.ui.graphics)
  implementation(libs.androidx.compose.ui.text.google.fonts)
  implementation(libs.androidx.compose.ui.tooling.preview)
  implementation(libs.androidx.core.ktx)
  implementation(libs.androidx.lifecycle.runtime.ktx)
  // Media3
  implementation(libs.androidx.media3.common) // Shared media functionality
  implementation(libs.androidx.media3.exoplayer) // Core player functionality (ExoPlayer)
  implementation(libs.androidx.media3.session) // For background playback and system notification controls
  implementation(libs.androidx.media3.ui) // UI components for the player (like PlayerView)
  implementation(libs.androidx.media3.ui.compose.material3) // For Material 3 Compose components
  // DataStore
  implementation(libs.androidx.datastore.preferences)
  // Dagger Hilt Core
  implementation(libs.hilt.android)
  ksp(libs.hilt.android.compiler)
  // Jetpack Compose Hilt Integration
  implementation(libs.androidx.hilt.navigation.compose)
  // Kotlin JSON Serialization
  implementation(libs.kotlinx.serialization.json)

  testImplementation(libs.junit)

  androidTestImplementation(platform(libs.androidx.compose.bom))
  androidTestImplementation(libs.androidx.compose.ui.test.junit4)
  androidTestImplementation(libs.androidx.espresso.core)
  androidTestImplementation(libs.androidx.junit)

  debugImplementation(libs.androidx.compose.ui.test.manifest)
  debugImplementation(libs.androidx.compose.ui.tooling)
}