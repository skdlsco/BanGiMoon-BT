package com.minsa.baingimoon_bt

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * Created by eka on 2018. 4. 1..
 */

interface NetworkAPI {
    @FormUrlEncoded
    @POST("/setData")
    fun setDevices(@Field("lat") lat: String,
                   @Field("lon") lon: String): Call<ResponseBody>
}