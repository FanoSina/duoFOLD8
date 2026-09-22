plugins { id("com.android.application"); id("org.jetbrains.kotlin.android") }
android {
 namespace = "com.fano.duofold"; compileSdk = 35
 defaultConfig { applicationId = "com.fano.duofold"; minSdk = 31; targetSdk = 35; versionCode = 1; versionName = "0.1" }
 compileOptions { sourceCompatibility = JavaVersion.VERSION_17; targetCompatibility = JavaVersion.VERSION_17 }
 kotlinOptions { jvmTarget = "17" }
}
dependencies {
 implementation("androidx.core:core-ktx:1.15.0")
 implementation("androidx.activity:activity-ktx:1.10.0")
 implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.7")
 implementation("androidx.window:window:1.3.0")
}