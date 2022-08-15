package com.sdjucj.framework.base.adapter.holder

import androidx.recyclerview.widget.RecyclerView

/**
 * desc:ViewHolderSubscriber
 * Time:2022/8/15
 * author:CJ
 **/
interface ViewHolderSubscriber {
    fun onBindViewHolder(position: Int, payloads: List<Any>)

    fun unBindViewHolder(position: Int)

    fun onViewAttachedToWindow(holder: RecyclerView.ViewHolder, position: Int)

    fun onViewDetachedFromWindow(holder: RecyclerView.ViewHolder, position: Int)
}