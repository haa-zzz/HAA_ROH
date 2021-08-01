package com.example.haa_roh.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter

/**
 * author : Haa-zzz
 * time : 2021/8/1
 * 处理图片，把获取到的 base64编码的图片 转换为Bitmap 并通过 DataBinding的适配器来添加进去
 */
@BindingAdapter("imgUrl")
fun loadImage(imageView: ImageView?, base64Image : String?) {
    if(base64Image != null){
        val bitmap = base64Decode(base64Image)
        imageView?.setImageBitmap(bitmap)
    }
}