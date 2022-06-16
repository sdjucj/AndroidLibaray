@file:Suppress("unused")

package com.sdjucj.framework.utils

import android.net.Uri
import android.os.Build
import androidx.core.content.FileProvider
import java.io.File

/**
 * desc:文件处理工具类
 * Time:2022/6/16
 * author:CJ
 **/

var fileProviderAuthority: String = "${packageName}.provider"

fun File.toUri(authority: String = fileProviderAuthority): Uri =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        FileProvider.getUriForFile(sApplication, authority, this)
    } else {
        Uri.fromFile(this)
    }

/**
 * 文件路径转Uri
 */
fun filePathToUri(filePath: String): Uri = File(filePath).toUri()

