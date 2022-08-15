package com.sdjucj.framework.base.adapter

/**
 * desc:IAdapter
 * Time:2022/8/15
 * author:CJ
 **/
interface IAdapter<VP> {
    fun getItem(position: Int): VP?
}