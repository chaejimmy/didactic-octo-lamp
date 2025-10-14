plugins {
    alias(libs.plugins.pacedream.android.library)
    alias(libs.plugins.pacedream.android.library.compose)
    alias(libs.plugins.pacedream.android.library.jacoco)
}

android {
    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    namespace = "com.shourov.apps.pacedream.core.ui"
}

dependencies {
    api(libs.androidx.metrics)
    api(projects.core.analytics)
    api(projects.core.designsystem)
    api(projects.core.model)
    api(projects.core.data)
    api(projects.core.common)

    implementation(libs.androidx.browser)
    implementation(libs.coil.kt)
    implementation(libs.coil.kt.compose)
    implementation(libs.libphonenumber)
    implementation(libs.androidx.auto.fill)
    implementation(libs.country.code.picker)
    implementation(libs.lottie.compose)
    implementation(libs.play.services.auth.api.phone)
//    implementation(libs.firebase.auth.ktx)
    implementation(libs.google.firebase.auth)
    implementation(project(":common"))

}
