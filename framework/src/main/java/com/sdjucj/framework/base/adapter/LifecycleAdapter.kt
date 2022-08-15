package com.sdjucj.framework.base.adapter

import android.annotation.SuppressLint
import android.util.SparseArray
import androidx.core.util.forEach
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner

/**
 * desc:LifecycleAdapter
 * Time:2022/8/15
 * author:CJ
 **/
@SuppressLint("RestrictedApi")
interface LifecycleAdapter : LifecycleEventObserver {

    val lifecycleObservers: SparseArray<(source: LifecycleOwner, event: Lifecycle.Event) -> Boolean>

    fun registerLifecycleObserver(
        key: Int,
        observer: (LifecycleOwner, Lifecycle.Event) -> Boolean
    ) {
        if (lifecycleObservers.indexOfKey(key) < 0) {
            lifecycleObservers.put(key, observer)
        }
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        lifecycleObservers.forEach { _, value ->
            value.invoke(source, event)
        }
        if (Lifecycle.Event.ON_DESTROY == event) {
            lifecycleObservers.clear()
        }
    }
}