package com.sdjucj.framework.utils

import android.content.res.Resources
import android.util.DisplayMetrics

/**
 * desc:尺寸工具类
 * Time:2022/4/22
 * author:CJ
 **/
object SizeUtils {

    /**
     * Value of dp to value of px.
     */
    fun dp2px(dp: Float): Int {
        val scale = Resources.getSystem().displayMetrics.density
        return (dp * scale + 0.5f).toInt()
    }

    /**
     * Value of px to value of dp.
     */
    fun px2dp(px: Float): Int {
        val scale = Resources.getSystem().displayMetrics.density
        return (px / scale + 0.5f).toInt()
    }

    /**
     * Value of sp to value of px.
     */
    fun sp2px(sp: Float): Int {
        val fontScale = Resources.getSystem().displayMetrics.scaledDensity
        return (sp * fontScale + 0.5f).toInt()
    }

    /**
     * Value of sp to value of px.
     */
    fun px2sp(px: Float): Int {
        val fontScale = Resources.getSystem().displayMetrics.scaledDensity
        return (px / fontScale + 0.5f).toInt()
    }

    /**
     * Value of screen width of px
     */
    fun getScreenWidth(): Int {
        val dm: DisplayMetrics = Resources.getSystem().displayMetrics
        return dm.widthPixels
    }

    /**
     * Value of screen height of px
     */
    fun getScreenHeight(): Int {
        val dm: DisplayMetrics = Resources.getSystem().displayMetrics
        return dm.heightPixels
    }

}