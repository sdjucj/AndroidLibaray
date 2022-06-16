@file:Suppress("unused")

package com.sdjucj.framework.utils

import android.app.Activity
import android.content.ContextWrapper
import android.view.View
import android.view.Window
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.cj.framework.R
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * desc:View工具类
 * Time:2022/6/15
 * author:CJ
 **/

val View.window: Window?
    get() {
        var context = this.context
        while (context is ContextWrapper) {
            if (context is Activity) {
                return context.window
            }
            context = context.baseContext
        }
        return null
    }
//todo chenjing 完善