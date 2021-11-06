package com.example.haa_roh.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.haa_roh.R
import com.example.haa_roh.api.RecyclerViewCallBack
import com.example.haa_roh.base.BaseApplication.Companion.getContext
import com.example.haa_roh.bean.room.PlanRoom
import com.example.haa_roh.databinding.ItemBinding
import com.example.haa_roh.util.printLog

/**
 * author : Haa-zzz
 * time : 2021/8/2
 * PlanFragment中recyclerView的适配器类，通过DataBinding类优化 ViewHolder，减少代码量
 * 参考：https://juejin.cn/post/6844903955693043725
 */
class PlanAdapter(private val list: ArrayList<PlanRoom>, private val callBack: RecyclerViewCallBack) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding: ItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.item, parent, false)

        return NewViewHolder(binding)
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewHolder : NewViewHolder  = holder as NewViewHolder
        val binding = viewHolder.binding as ItemBinding

        //在这里就可以设置数据了
        binding.tag = list[position].tag
        binding.title = list[position].title

        binding.root.setOnClickListener{
            callBack.callBack(position)
        }
        binding.itemDelete.setOnClickListener{
            callBack.deleteClickBack(position,list[position].id)
        }
        binding.itemSure.setOnClickListener{
            callBack.sureClickBack(position,list[position].id)
        }

    }
    override fun getItemCount(): Int {
        return list.size
    }
}