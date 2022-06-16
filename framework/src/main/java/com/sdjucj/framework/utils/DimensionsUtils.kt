@file:Suppress("unused")

package com.sdjucj.framework.utils

import android.content.res.Resources
import android.util.TypedValue

/**
 * desc:尺寸转换工具类
 * Time:2022/4/22
 * author:CJ
 **/

inline val Int.dp: Float get() = toFloat().dp

inline val Long.dp: Float get() = toFloat().dp

inline val Double.dp: Float get() = toFloat().dp

inline val Float.dp: Float
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this,
        Resources.getSystem().displayMetrics
    )

inline val Int.sp: Float get() = toFloat().sp

inline val Long.sp: Float get() = toFloat().sp

inline val Double.sp: Float get() = toFloat().sp

inline val Float.sp: Float
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_SP,
        this,
        Resources.getSystem().displayMetrics
    )

fun Int.dp2px(): Int = toFloat().dp2px()

fun Long.dp2px(): Int = toFloat().dp2px()

fun Double.dp2px(): Int = toFloat().dp2px()

fun Float.dp2px(): Int = (this * Resources.getSystem().displayMetrics.density + 0.5F).toInt()

fun Int.px2dp(): Int = toFloat().px2dp()

fun Long.px2dp(): Int = toFloat().px2dp()

fun Double.px2dp(): Int = toFloat().px2dp()

fun Float.px2dp(): Int = (this / Resources.getSystem().displayMetrics.density + 0.5F).toInt()

fun Int.sp2px(): Int = toFloat().sp2px()

fun Long.sp2px(): Int = toFloat().sp2px()

fun Double.sp2px(): Int = toFloat().sp2px()

fun Float.sp2px(): Int = (this * Resources.getSystem().displayMetrics.scaledDensity + 0.5F).toInt()

fun Int.px2sp(): Int = toFloat().px2sp()

fun Long.px2sp(): Int = toFloat().px2sp()

fun Double.px2sp(): Int = toFloat().px2sp()

fun Float.px2sp(): Int = (this / Resources.getSystem().displayMetrics.scaledDensity + 0.5F).toInt()
