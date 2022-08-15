package com.sdjucj.framework.base.adapter.providers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sdjucj.framework.base.adapter.holder.DefaultViewHolder

/**
 * desc:DefaultViewModel
 * Time:2022/8/15
 * author:CJ
 **/
abstract class DefaultAdapterViewProvider<T>(override val layoutRes: Int, override var item: T) :
    AdapterViewProvider<T, DefaultViewHolder> {

    /**
     * view初始化
     */
    var initView: ((DefaultViewHolder) -> Unit)? = null

    override var isFirstInit: Boolean = true

    override fun getHolderItemView(parent: ViewGroup, layoutInflater: LayoutInflater): View {
        return layoutInflater.inflate(layoutRes, parent, false)
    }

    override fun getViewHolder(
        parent: ViewGroup,
        layoutInflater: LayoutInflater
    ): DefaultViewHolder {
        return DefaultViewHolder(getHolderItemView(parent, layoutInflater)).apply {
            initView?.invoke(this)
        }
    }


}