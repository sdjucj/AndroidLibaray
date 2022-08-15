package com.sdjucj.androidlibaray

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.sdjucj.framework.base.adapter.ListAdapter
import com.sdjucj.framework.base.adapter.getView
import com.sdjucj.framework.base.adapter.holder.DefaultViewHolder
import com.sdjucj.framework.base.adapter.into
import com.sdjucj.framework.base.adapter.providers.DefaultAdapterViewProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.random.Random

/**
 * desc:RecyclerView Adapter 测试
 * Time:2022/8/15
 * author:CJ
 **/
class RecyclerViewAdapterTestActivity:AppCompatActivity() {

    private lateinit var adapter: ListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_recycler_view_adapter_test)

        adapter = ListAdapter().into(
            findViewById(R.id.recyclerView),
            LinearLayoutManager(this@RecyclerViewAdapterTestActivity)
        ) as ListAdapter

        findViewById<Button>(R.id.btStart).setOnClickListener {
            /*lifecycleScope.launch {
                val list = ArrayList<ArrayViewModelTest>()
                withContext(Dispatchers.IO){

                    for(index in 0 until 10000){
                        list.add(ArrayViewModelTest(
                            R.layout.item_list, ModelTest(
                                "标题${index}",
                                if (Random.nextInt(2) == 1) "副标题副标题副标题副标题副标题" else "副标题"
                            )
                        ))
                    }
                }
                adapter.addAll(list)
            }*/

            adapter.add(
                ArrayViewModelTest(
                    R.layout.item_list, ModelTest(
                        "标题",
                        if (Random.nextInt(2) == 1) "副标题副标题副标题副标题副标题" else "副标题"
                    )
                )
            )
            /*adapter.add(
                BindingAdapterViewProvider(
                    R.layout.item_list,
                    BR.ModelTest,
                    ModelTest(
                        "标题",
                        if (kotlin.random.Random.nextInt(2) == 1) "副标题副标题副标题副标题副标题" else "副标题"
                    ),
                )
            )*/
        }
    }

    private class ArrayViewModelTest(override val layoutRes: Int, override var item: ModelTest) :
        DefaultAdapterViewProvider<ModelTest>(layoutRes, item) {
        override fun bindVH(viewHolder: DefaultViewHolder, payloads: List<Any>) {
            val titleText = viewHolder.getView<TextView>(R.id.tvTitle)
            val subTitleText = viewHolder.getView<TextView>(R.id.tvSubtitle)
            //val item = viewHolder.getItem<ModelTest>()
            titleText.text = item.title
            subTitleText.text = item.subTitle
            viewHolder.itemView.setOnClickListener {
                /* val vm = viewHolder.getViewModel<ArrayViewModelTest>()
                 vm?.model?.title = "${Random().nextInt(100)}"
                 viewHolder.getAdapter<ListAdapter>()?.run {
                     setItem(viewHolder.adapterPosition, vm)

                 }*/
            }
            viewHolder.onViewAttachedToWindow {
                //firstAnimation()
                //updateAnimation()
            }
            viewHolder.onBindViewHolder { position, payloads ->
                /*val item = viewHolder.getItem<ModelTest>()
                titleText.text = item?.title
                subTitleText.text = item?.subTitle*/
            }
        }
    }

    data class ModelTest(var title: String, var subTitle: String)

}