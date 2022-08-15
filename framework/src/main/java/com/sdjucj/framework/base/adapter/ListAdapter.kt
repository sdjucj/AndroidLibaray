@file:Suppress("unused")

package com.sdjucj.framework.base.adapter

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.sdjucj.framework.base.adapter.providers.AdapterViewProviderType

/**
 * desc:ListAdapter
 * Time:2022/8/15
 * author:CJ
 **/
class ListAdapter : BaseAdapter<AdapterViewProviderType>() {

    private var data = mutableListOf<AdapterViewProviderType>()

    fun clear() {
        val oldSize = itemCount
        data.clear()
        if (oldSize <= 0) {
            return
        }
        notifyItemRangeRemoved(0, oldSize)
    }

    fun add(item: AdapterViewProviderType): Boolean {
        return data.add(item).apply {
            notifyItemRangeInserted(itemCount - 1, 1)
        }
    }

    fun add(position: Int, item: AdapterViewProviderType) {
        return data.add(position, item).apply {
            notifyItemRangeInserted(position, 1)
        }
    }

    fun addAll(items: Collection<AdapterViewProviderType>): Boolean {
        val oldSize = itemCount
        return data.addAll(items).apply {
            if (this) {
                notifyItemRangeInserted(oldSize, itemCount - oldSize)
            }
        }
    }

    fun remove(item: AdapterViewProviderType): Boolean {
        return data.indexOf(item).takeIf { it >= 0 }?.run {
            removeAt(this)
            true
        } ?: false
    }

    fun removeAt(position: Int) {
        data.removeAt(position)
        notifyItemRangeRemoved(position, 1)
    }

    fun setItem(position: Int, item: AdapterViewProviderType?) {
        item?.run {
            data[position] = item
            notifyItemChanged(position)
        }
    }

    fun setList(items: Collection<AdapterViewProviderType>) {
        data.clear()
        addAll(items)
    }

    override fun getItemCount(): Int {
        return data.size
    }


    override fun getItem(position: Int): AdapterViewProviderType {
        return data[position]
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        data.forEach {
            if (it is Lifecycle) {
                it.onStateChanged(source, event)
            }
        }
        if (Lifecycle.Event.ON_DESTROY == event) {
            data.clear()
        }
        super.onStateChanged(source, event)
    }

}