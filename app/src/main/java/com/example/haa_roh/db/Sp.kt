package com.example.haa_roh.db

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.example.haa_roh.base.BaseApplication.Companion.getContext
//第一步：获取Sharedpreferences对象
val sharedPreferences: SharedPreferences = getContext().getSharedPreferences("hor", MODE_PRIVATE)
fun saveToSp( phone : String , isLogin : Boolean){
    //第二步：获取SharedPreferences.Editor对象
    val editor = sharedPreferences.edit()
    //第三步，添加数据
    editor.putString("number", phone)
    editor.putBoolean("isLogin",isLogin)
    //第四步：提交
    editor.apply()
}
fun querySpIsLogin() : Boolean{
    return sharedPreferences.getBoolean("isLogin",false)
}
fun querySpNumber() : String?{
    return sharedPreferences.getString("number",null)
}