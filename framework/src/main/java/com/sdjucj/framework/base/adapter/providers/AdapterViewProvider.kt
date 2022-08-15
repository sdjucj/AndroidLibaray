@file:Suppress("unused")

package com.sdjucj.framework.base.adapter.providers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.sdjucj.framework.base.adapter.holder.ViewHolderFactory

/**
 * desc:ViewModel
 * Time:2022/8/15
 * author:CJ
 **/
/**
 * AdapterViewProvider 定义别名
 */
typealias AdapterViewProviderType = AdapterViewProvider<*, *>

interface AdapterViewProvider<T, VH : RecyclerView.ViewHolder> : ViewHolderFactory<VH> {
    /**
     * item布局
     */
    @get:LayoutRes
    val layoutRes: Int

    /**
     * 是否第一次初始化
     */
    var isFirstInit: Boolean

    /**
     * item数据
     */
    var item: T

    /**
     * item类型
     */
    var itemViewType: Int
        get() = layoutRes
        set(value) {}

    /**
     * 设置itemView
     */
    fun getHolderItemView(parent: ViewGroup, layoutInflater: LayoutInflater): View

    /**
     * 数据绑定
     */
    fun bindVH(viewHolder: VH, payloads: List<Any>)

    /**
     * 解绑
     */
    fun unbindVH(viewHolder: VH) {}

    /**
     * 生命周期变化监听
     */
    fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {}
}