package com.minsa.baingimoon_bt

/**
 * Created by eka on 2018. 4. 1..
 */
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkHelper {
    private const val url = "http://soylatte.kr:4102/"

    private var retrofit: Retrofit? = null
    val retrofitInstance: NetworkAPI
        get() {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                        .baseUrl(url)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
            }
            return retrofit!!.create<NetworkAPI>(NetworkAPI::class.java)
        }
}