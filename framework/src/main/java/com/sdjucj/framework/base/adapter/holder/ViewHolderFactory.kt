package com.sdjucj.framework.base.adapter.holder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * desc:ViewHolderFactory
 * Time:2022/8/15
 * author:CJ
 **/
interface ViewHolderFactory<VH : RecyclerView.ViewHolder> {
    fun getViewHolder(parent: ViewGroup, layoutInflater: LayoutInflater): VH
}