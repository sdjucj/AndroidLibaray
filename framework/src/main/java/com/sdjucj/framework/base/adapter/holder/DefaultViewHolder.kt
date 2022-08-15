@file:Suppress("unused")

package com.sdjucj.framework.base.adapter.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * desc:DefaultViewHolder
 * Time:2022/8/15
 * author:CJ
 **/
class DefaultViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), ViewHolderSubscriber {

    private var bindView: ((Int, List<Any>) -> Unit)? = null
    private var unbindView: ((Int) -> Unit)? = null
    private var onViewAttached: ((DefaultViewHolder) -> Unit)? = null
    private var onViewDetached: ((DefaultViewHolder) -> Unit)? = null

    fun onBindViewHolder(bindView:  ((Int, List<Any>) -> Unit)?) {
        this.bindView = bindView
    }

    fun onUnBindViewHolder(unbindView: ((Int) -> Unit)?) {
        this.unbindView = unbindView
    }

    fun onViewAttachedToWindow(onViewAttached:  ((DefaultViewHolder) -> Unit)?) {
        this.onViewAttached = onViewAttached
    }

    fun onViewDetachedFromWindow(onViewDetached:  ((DefaultViewHolder) -> Unit)?) {
        this.onViewDetached = onViewDetached
    }

    override fun onBindViewHolder(position: Int, payloads: List<Any>) {
        bindView?.invoke(position, payloads)
    }

    override fun unBindViewHolder(position: Int) {
        unbindView?.invoke(position)
    }

    override fun onViewAttachedToWindow(holder: RecyclerView.ViewHolder, position: Int) {
        onViewAttached?.invoke(holder as DefaultViewHolder)
    }

    override fun onViewDetachedFromWindow(holder: RecyclerView.ViewHolder, position: Int) {
        onViewDetached?.invoke(holder as DefaultViewHolder)
    }

}