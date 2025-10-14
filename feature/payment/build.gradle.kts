plugins {
    alias(libs.plugins.pacedream.android.library.compose)
    alias(libs.plugins.pacedream.android.feature)
}

android {
    namespace = "com.shourov.apps.pacedream.feature.payment"
}


dependencies {
    implementation(libs.accompanist.permissions)
    implementation(projects.core.data)
}