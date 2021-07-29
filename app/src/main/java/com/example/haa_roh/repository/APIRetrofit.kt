package com.example.haa_roh.repository

import androidx.lifecycle.MutableLiveData
import cn.bmob.v3.BmobSMS
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.QueryListener
import com.example.haa_roh.bean.uiBean.CountChange
import com.example.haa_roh.bean.uiBean.LoginAutoCode
import com.example.haa_roh.util.LOGGED
import com.example.haa_roh.util.oneMinuteCountdown
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit


fun retrofitGetAutoCode(phoneNumber : String) {

//    val single : Single<AutoCodeState> =
//        Retrofit.Builder()
//        .baseUrl("https://api2.bmob.cn/1/")
//        .addConverterFactory(GsonConverterFactory.create())
//        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
//        .client(getOkHttpClient())
//        .build()
//        .create(ApiService::class.java)
//        .apiGetAutoCode(phoneNumber)
//
//    single.subscribeOn(Schedulers.newThread())
//        .observeOn(Schedulers.io())
//        .subscribe(object : SingleObserver<AutoCodeState>{
//            override fun onSuccess(t: AutoCodeState?) {
//                if (t != null) {
//                    LOGGED("成功：${t.smsId}")
//
//                }
//            }
//            override fun onError(e: Throwable?) {
//                LOGGED("失败：${e.toString()}")
//
//            }
//            override fun onSubscribe(d: Disposable?) {
//            }
//        })

}


private fun getOkHttpClient() : OkHttpClient {
    return OkHttpClient.Builder()
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(10, TimeUnit.SECONDS)
        .build()
}