package com.shourov.apps.pacedream.core.network.remote.interceptor

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.content.pm.PackageInfoCompat
import okhttp3.Interceptor
import okhttp3.Response

class UserAgentInterceptor(private val context: Context) : Interceptor {

    companion object {
        private const val USER_AGENT = "User-Agent"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(
            chain.request().newBuilder().apply {
                header(USER_AGENT, buildUserAgent(context))
            }.build()
        )
    }

    private fun buildUserAgent(context: Context): String {
        with(context.packageManager) {
            val versionName = try {
                getPackageInfo(context.packageName, 0).versionName
            } catch (e: PackageManager.NameNotFoundException) {
                "nameNotFound"
            }
            val versionCode = try {
                PackageInfoCompat.getLongVersionCode(getPackageInfo(context.packageName, 0))
            } catch (e: PackageManager.NameNotFoundException) {
                "versionCodeNotFound"
            }

            val applicationInfo = context.applicationInfo
            val stringId = applicationInfo.labelRes
            val appName =
                if (stringId == 0) applicationInfo.nonLocalizedLabel.toString()
                else context.getString(stringId)

            val manufacturer = Build.MANUFACTURER
            val model = Build.MODEL
            val version = Build.VERSION.SDK_INT
            val versionRelease = Build.VERSION.RELEASE
            val buildId = Build.ID

            return "$appName / $versionName($versionCode) ($manufacturer; $model; SDK $version; Android $versionRelease; Build/$buildId)"
        }
    }
}
