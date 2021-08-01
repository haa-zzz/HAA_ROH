package com.example.haa_roh.util

import android.app.Activity
import android.os.Build
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.WindowManager
import android.widget.EditText


/**
 * author : Haa-zzz
 * time : 2021/8/1
 * 一些UI相关的帮助类
 */
/*
    监听EditView改变
 */
fun EditText.afterTextChanged(afterTextChanged : (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}

/*
 * 动态修改标题栏的颜色
 *
 * @param activity   Activity
 * @param colorResId 颜色ResourceID
 */
fun setWindowStatusBarColor(activity: Activity, colorResId: Int) {
    try {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = activity.window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            // 顶部状态栏
            window.statusBarColor = activity.resources.getColor(colorResId)
            // 底部导航栏
            // window.setNavigationBarColor(activity.getResources().getColor(colorResId));
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

/*
  打印：
 */
fun LOGGED(str : String){
    Log.d("logDInHaaRoh", "LOGD: $str")
}