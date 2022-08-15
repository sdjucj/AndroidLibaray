@file:Suppress("unused")

package com.sdjucj.framework.base.adapter

import android.util.SparseArray
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.sdjucj.framework.R
import com.sdjucj.framework.base.adapter.holder.ViewHolderSubscriber
import com.sdjucj.framework.base.adapter.providers.AdapterViewProvider
import com.sdjucj.framework.base.adapter.providers.AdapterViewProviderType
import java.lang.ref.WeakReference

/**
 * desc:BaseAdapter
 * Time:2022/8/15
 * author:CJ
 **/
@Suppress("UNCHECKED_CAST")
abstract class BaseAdapter<VP : AdapterViewProviderType> : RecyclerView.Adapter<RecyclerView.ViewHolder>(),
    IAdapter<VP>, LifecycleAdapter {

    private val viewHolderFactoryCache = ViewHolderFactoryCache()
    private val sparseArrayLayoutInflater = SparseArray<WeakReference<LayoutInflater>>(1)
    private var recyclerView: RecyclerView? = null

    override val lifecycleObservers: SparseArray<(source: LifecycleOwner, event: Lifecycle.Event) -> Boolean> =
        SparseArray()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val viewHolder = viewHolderFactoryCache.get(viewType).getViewHolder(
            parent, sparseArrayLayoutInflater.get(0).get() ?: LayoutInflater.from(parent.context)
        )
        viewHolder.itemView.setTag(R.id.adapter, this)
        viewHolder.itemView.setTag(R.id.adapter_recyclerView, recyclerView)
        return viewHolder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    }


    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (position == RecyclerView.NO_POSITION) {
            return
        }
        holder.itemView.setTag(R.id.adapter, this)
        holder.itemView.setTag(R.id.adapter_recyclerView, recyclerView)
        getItem(position)?.apply {
            this as AdapterViewProvider<*, RecyclerView.ViewHolder>
            holder.itemView.setTag(R.id.adapter_item, this)
            bindVH(holder, payloads)
            if (holder is ViewHolderSubscriber) {
                holder.onBindViewHolder(position, payloads)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position) ?: return 0
        viewHolderFactoryCache.register(item.itemViewType, item)
        return item.itemViewType
    }

    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        if (holder is ViewHolderSubscriber) {
            holder.unBindViewHolder(holder.adapterPosition)
        }
        holder.getAdapterViewProvider<AdapterViewProvider<*, RecyclerView.ViewHolder>>()?.apply {
            unbindVH(holder)
        }
        holder.itemView.setTag(R.id.adapter_item, null)
        holder.itemView.setTag(R.id.adapter, null)
        holder.itemView.setTag(R.id.adapter_recyclerView, null)
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        sparseArrayLayoutInflater.append(
            0,
            WeakReference(LayoutInflater.from(recyclerView.context))
        )
        this.recyclerView = recyclerView
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        viewHolderFactoryCache.clear()
        sparseArrayLayoutInflater.clear()
        this.recyclerView = null
    }

    override fun onViewAttachedToWindow(holder: RecyclerView.ViewHolder) {
        if (holder is ViewHolderSubscriber) {
            holder.onViewAttachedToWindow(holder, holder.adapterPosition)
            holder.getAdapterViewProvider<AdapterViewProviderType>()?.isFirstInit = false
        }
    }

    override fun onViewDetachedFromWindow(holder: RecyclerView.ViewHolder) {
        if (holder is ViewHolderSubscriber) {
            holder.onViewDetachedFromWindow(holder, holder.adapterPosition)
        }
    }


    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        if (Lifecycle.Event.ON_DESTROY == event) {
            this.recyclerView?.adapter = null
            source.lifecycle.removeObserver(this)
        }
        super.onStateChanged(source, event)
    }

    private class ViewHolderFactoryCache {
        private val typeInstances = SparseArray<AdapterViewProviderType>()

        fun register(type: Int, item: AdapterViewProviderType): Boolean {
            return if (!contains(type)) {
                typeInstances.put(type, item)
                return true
            } else {
                false
            }
        }

        fun get(type: Int): AdapterViewProviderType {
            return typeInstances.get(type)
        }

        fun contains(type: Int) = typeInstances.indexOfKey(type) >= 0

        fun clear() {
            typeInstances.clear()
        }
    }

}