package com.sdjucj.framework.utils

import android.content.Intent

/**
 * desc:intent管理工具类
 * Time:2022/6/15
 * author:CJ
 **/

fun Intent.startActivity(): Boolean =
    try {
        currentActivity.startActivity(this)
        true
    } catch (e: Exception) {
        e.printStackTrace()
        false
    }
//todo chenjing 完善