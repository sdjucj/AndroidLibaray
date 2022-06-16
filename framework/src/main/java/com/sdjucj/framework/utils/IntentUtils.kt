package com.sdjucj.framework.utils

import android.content.Intent
import android.os.Build
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts

/**
 * desc:intent管理工具类
 * Time:2022/6/15
 * author:CJ
 **/

fun Intent.newTask(): Intent = addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

fun Intent.grantReadUriPermission(): Intent = apply {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
    }
}

fun Intent.defaultCategory(): Intent = addCategory(Intent.CATEGORY_DEFAULT)

fun Intent.startActivity(): Boolean =
    try {
        currentActivity.startActivity(this)
        true
    } catch (e: Exception) {
        e.printStackTrace()
        false
    }

fun Intent.startActivityForResult(
    onActivityResult: ((activityResult: ActivityResult?) -> Unit)? = null
) {
    if (currentActivity is ComponentActivity) {
        (currentActivity as ComponentActivity).registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            onActivityResult?.invoke(it)
        }.launch(this)
    } else {
        throw IllegalStateException("just ComponentActivity can use it")
    }

}

//todo chenjing 完善