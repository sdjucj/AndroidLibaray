package com.sdjucj.framework.utils

import android.app.Activity
import android.content.Intent
import java.util.*

/**
 * desc:Activity管理工具类
 * Time:2022/6/15
 * author:CJ
 **/

/**
 * activity栈
 */
internal val activityStack = LinkedList<Activity>()

/**
 * 读取activity
 */
val currentActivity: Activity get() = activityStack.last()

fun startActivity(intent: Intent) = currentActivity.startActivity(intent)

//todo chenjing 完善