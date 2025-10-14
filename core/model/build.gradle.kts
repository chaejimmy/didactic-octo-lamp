plugins {
    alias(libs.plugins.pacedream.jvm.library)
}

dependencies {
    api(libs.kotlinx.datetime)
    implementation(libs.moshi)
    implementation(libs.moshi.adapters)
    implementation(libs.moshi.codegen)
    implementation(libs.gson.convert)
    implementation(libs.retrofit.gson)
}
