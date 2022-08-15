@file:Suppress("unused")

package com.sdjucj.framework.base.adapter

import android.view.View
import androidx.annotation.IdRes
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sdjucj.framework.R
import com.sdjucj.framework.base.adapter.providers.AdapterViewProvider

/**
 * desc:Utils
 * Time:2022/8/15
 * author:CJ
 **/
inline fun <reified Adapter> RecyclerView.ViewHolder.getAdapter(): Adapter? {
    return itemView.getTag(R.id.adapter) as? Adapter
}

inline fun <reified VP : AdapterViewProvider<*, *>> RecyclerView.ViewHolder.getAdapterViewProvider(): VP? {
    return itemView.getTag(R.id.adapter_item) as? VP
}

@Suppress("UNCHECKED_CAST")
inline fun <reified T> RecyclerView.ViewHolder.getItem(): T? {
    return (itemView.getTag(R.id.adapter_item) as? AdapterViewProvider<T, *>)?.item
}

inline fun <reified T : View> RecyclerView.ViewHolder.getView(@IdRes viewId: Int): T {
    return itemView.findViewById(viewId) as T
}

fun RecyclerView.ViewHolder.getBinding(): ViewDataBinding {
    return itemView.getTag(R.id.list_adapter_binding) as ViewDataBinding
}

fun RecyclerView.Adapter<*>.into(
    recyclerView: RecyclerView,
    layoutManager: RecyclerView.LayoutManager? = null
): RecyclerView.Adapter<*> = apply {
    recyclerView.layoutManager = layoutManager ?: LinearLayoutManager(recyclerView.context)
    recyclerView.adapter = this
    if (this is LifecycleAdapter) {
        val context = recyclerView.context
        if (context is LifecycleOwner) {
            context.lifecycle.addObserver(this)
        }
    }
}
