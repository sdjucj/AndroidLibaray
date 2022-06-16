@file:Suppress("unused")

package com.sdjucj.framework

/**
 * desc:app自动初始化
 * Time:2022/6/15
 * author:CJ
 **/
import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import androidx.startup.Initializer
import com.sdjucj.framework.utils.activityStack
import com.sdjucj.framework.utils.sApplication

internal class AppAutoInitializer : Initializer<Unit> {

    override fun create(context: Context) {
        sApplication = context as Application
        sApplication.activityLifecycle(
            onActivityCreated = { activity, _ ->
                activityStack.add(activity)
            },
            onActivityDestroyed = { activity ->
                activityStack.remove(activity)
            }
        )
    }

    override fun dependencies() = emptyList<Class<Initializer<*>>>()

    private fun Application.activityLifecycle(
        onActivityCreated: ((Activity, Bundle?) -> Unit)? = null,
        onActivityStarted: ((Activity) -> Unit)? = null,
        onActivityResumed: ((Activity) -> Unit)? = null,
        onActivityPaused: ((Activity) -> Unit)? = null,
        onActivityStopped: ((Activity) -> Unit)? = null,
        onActivitySaveInstanceState: ((Activity, Bundle?) -> Unit)? = null,
        onActivityDestroyed: ((Activity) -> Unit)? = null,
    ): Application.ActivityLifecycleCallbacks =
        object : Application.ActivityLifecycleCallbacks {
            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                onActivityCreated?.invoke(activity, savedInstanceState)
            }

            override fun onActivityStarted(activity: Activity) {
                onActivityStarted?.invoke(activity)
            }

            override fun onActivityResumed(activity: Activity) {
                onActivityResumed?.invoke(activity)
            }

            override fun onActivityPaused(activity: Activity) {
                onActivityPaused?.invoke(activity)
            }

            override fun onActivityStopped(activity: Activity) {
                onActivityStopped?.invoke(activity)
            }

            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
                onActivitySaveInstanceState?.invoke(activity, outState)
            }

            override fun onActivityDestroyed(activity: Activity) {
                onActivityDestroyed?.invoke(activity)
            }

        }.also { registerActivityLifecycleCallbacks(it) }

}

