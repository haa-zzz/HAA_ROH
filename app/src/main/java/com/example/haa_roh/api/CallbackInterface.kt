package com.example.haa_roh.api

import android.view.View

interface RecyclerViewCallBack{
    fun callBack(index : Int)
    fun deleteClickBack(index : Int, id : String)
    fun sureClickBack(index : Int,id : String)
}