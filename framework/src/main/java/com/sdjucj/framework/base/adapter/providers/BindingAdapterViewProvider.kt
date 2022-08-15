package com.sdjucj.framework.base.adapter.providers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.sdjucj.framework.R
import com.sdjucj.framework.base.adapter.getBinding
import com.sdjucj.framework.base.adapter.holder.DefaultViewHolder

/**
 * desc:BindingViewModel
 * Time:2022/8/15
 * author:CJ
 **/
open class BindingAdapterViewProvider<T>(
    override val layoutRes: Int,
    private val variableId: Int,
    override var item: T,
) : DefaultAdapterViewProvider<T>(layoutRes, item) {

    override fun getHolderItemView(parent: ViewGroup, layoutInflater: LayoutInflater): View {
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            layoutInflater,
            layoutRes,
            parent,
            false
        )
        val view = binding.root
        view.setTag(R.id.list_adapter_binding, binding)
        return view
    }

    override fun bindVH(viewHolder: DefaultViewHolder, payloads: List<Any>) {
        viewHolder.getBinding().setVariable(variableId, item)
        viewHolder.getBinding().executePendingBindings()
    }

    override fun unbindVH(viewHolder: DefaultViewHolder) {
        viewHolder.getBinding().unbind()
    }
}