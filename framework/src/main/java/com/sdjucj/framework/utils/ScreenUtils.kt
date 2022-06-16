@file:Suppress("unused")

package com.sdjucj.framework.utils

import android.app.Activity
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Build
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat.Type.*
import androidx.fragment.app.Fragment

/**
 * desc:屏幕属性
 * Time:2022/6/15
 * author:CJ
 **/

/**
 * Value of screen width of px
 */
inline val screenWidth: Int get() = Resources.getSystem().displayMetrics.widthPixels

/**
 * Value of screen height of px
 */
inline val screenHeight: Int get() = Resources.getSystem().displayMetrics.heightPixels

fun Activity.isFullScreen(onResult: (isFullScreen: Boolean) -> Unit) {
    window?.decorView?.let { view ->
        view.post {
            val typeMask = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                systemBars()
            } else {
                // Caption bar is always false on API < 30
                statusBars() or navigationBars()
            }
            onResult.invoke(ViewCompat.getRootWindowInsets(view)?.isVisible(typeMask) != true)
        }
    } ?: onResult.invoke(false)
}

fun Activity.setFullScreen(isFullScreen: Boolean = false) {
    WindowCompat.getInsetsController(window, window.decorView).run {
        if (isFullScreen) hide(systemBars()) else show(systemBars())
    }
}

fun Fragment.isFullScreen(onResult: (isFullScreen: Boolean) -> Unit) {
    activity?.isFullScreen(onResult)
}

fun Fragment.setFullScreen(isFullScreen: Boolean = false) {
    activity?.setFullScreen(isFullScreen)
}

inline var Fragment.isLandscape: Boolean
    get() = activity?.isLandscape == true
    set(value) {
        activity?.isLandscape = value
    }

inline var Activity.isLandscape: Boolean
    get() = resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
    set(value) {
        requestedOrientation = if (value) {
            ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        } else {
            ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }
    }

inline var Fragment.isPortrait: Boolean
    get() = activity?.isPortrait == true
    set(value) {
        activity?.isPortrait = value
    }

inline var Activity.isPortrait: Boolean
    get() = resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT
    set(value) {
        requestedOrientation = if (value) {
            ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        } else {
            ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }
    }