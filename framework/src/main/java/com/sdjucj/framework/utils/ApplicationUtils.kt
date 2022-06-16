@file:Suppress("unused")

package com.sdjucj.framework.utils

import android.app.Application
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageInfo
import android.content.res.Configuration
import android.location.LocationManager
import android.net.Uri
import android.os.Process
import android.provider.Settings
import androidx.core.content.getSystemService
import androidx.core.content.pm.PackageInfoCompat

/**
 * desc:Application信息获取工具类
 * Time:2022/6/15
 * author:CJ
 **/

lateinit var sApplication: Application internal set

inline val packageName: String get() = sApplication.packageName

inline val packageInfo: PackageInfo
    get() = sApplication.packageManager.getPackageInfo(packageName, 0)

inline val appName: String
    get() = sApplication.applicationInfo.loadLabel(sApplication.packageManager).toString()

inline val versionName: String get() = packageInfo.versionName

inline val versionCode: Long get() = PackageInfoCompat.getLongVersionCode(packageInfo)

inline val isDebug: Boolean get() = sApplication.isDebug

inline val Application.isDebug: Boolean
    get() = packageManager.getApplicationInfo(
        packageName,
        0
    ).flags and ApplicationInfo.FLAG_DEBUGGABLE != 0

inline val isAppDarkMode: Boolean
    get() = (sApplication.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES

/**
 * gps是否可用
 */
inline val isLocationEnabled: Boolean
    get() = sApplication.getSystemService<LocationManager>()
        ?.isProviderEnabled(LocationManager.GPS_PROVIDER) == true

/**
 * 跳转到设置界面
 */
fun launchSettings(): Boolean =
    Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        .apply { data = Uri.fromParts("package", packageName, null) }
        .startActivity()

/**
 * 重启
 */
fun relaunch(killProcess: Boolean = true) =
    sApplication.packageManager.getLaunchIntentForPackage(packageName)?.let {
        it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(it)
        if (killProcess) Process.killProcess(Process.myPid())
    }
