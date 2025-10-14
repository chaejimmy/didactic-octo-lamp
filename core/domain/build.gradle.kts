plugins {
    alias(libs.plugins.pacedream.android.library)
    alias(libs.plugins.pacedream.android.library.jacoco)
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.shourov.apps.pacedream.domain"
}

dependencies {
    api(projects.core.data)
    api(projects.core.model)

    implementation(libs.javax.inject)

}