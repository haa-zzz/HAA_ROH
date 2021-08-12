package com.example.haa_roh.ui.adapter

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * author : Haa-zzz
 * time : 2021/8/2
 * ViewHolder的基类，供RecyclerView的Adapter使用，简化RecyclerView
 */

class NewViewHolder(var binding: ViewDataBinding) : RecyclerView.ViewHolder(
    binding.root
)