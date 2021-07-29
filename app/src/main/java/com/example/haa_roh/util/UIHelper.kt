package com.example.haa_roh.util

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText

/**
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

/**
  打印：
 */
fun LOGGED(str : String){
    Log.d("TAG", "LOGD: $str")
}