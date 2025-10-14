plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.pacedream.android.library.compose)
}

android {
    namespace = "com.pacedream.common"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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

    buildFeatures{
        compose = true
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
    //implementation(libs.compose.material3)
    implementation(libs.ui.text.google.fonts)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.test.espresso.core)

    //compose
   // implementation(libs.androidx.compose.runtime)
    //implementation(libs.androidx.compose.bom)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.material)

    implementation(libs.androidx.compose.material3)
    api(libs.androidx.compose.material.iconsExtended)

    //country picker
    implementation(libs.libphonenumber)
    implementation(libs.androidx.auto.fill)
    implementation(libs.country.code.picker)


}