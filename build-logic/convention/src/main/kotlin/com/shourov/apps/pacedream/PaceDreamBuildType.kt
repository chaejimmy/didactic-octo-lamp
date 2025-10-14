
package com.shourov.apps.pacedream

/**
 * This is shared between :app and :benchmarks module to provide configurations type safety.
 */
enum class PaceDreamBuildType(val applicationIdSuffix: String? = null) {
    DEBUG(".debug"),
    RELEASE,
}
