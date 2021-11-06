package com.example.haa_roh.repository

import com.example.haa_roh.bean.ImageData
import com.example.haa_roh.bean.Lishijr
import com.example.haa_roh.bean.Weather
import io.reactivex.rxjava3.core.Single
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

import java.util.*

interface ApiService {


    @GET("api/sjbz")
    fun apiPostImage(@Query("api_key") key : String = "527531122e478bb5",
                     @Query("method") method : String = "moblie",
                     @Query("type") type: String = "sina") : Single<ImageData>

    //https://api.muxiaoguo.cn/api/tianqi?city=西安&type=1

    @FormUrlEncoded
    @POST("api/tianqi")
    fun apiPostWeather(
        @Field("city") city: String = "西安",
        @Field("type") type: String = "1"
    ) : Single<Weather>

    //https://api.muxiaoguo.cn/api/lishijr
    @GET("api/lishijr")
    fun apiGetLiSh() : Single<Lishijr>
}