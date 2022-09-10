package com.mohsin.threesidedcubetest.network

import com.clarity.android.interview.network.NetworkApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.Retrofit.Builder
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class NetworkService {

    private val retrofit: Retrofit = Builder()
        .baseUrl("https://pokeapi.co/api/v2/")
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api: NetworkApi = retrofit.create(NetworkApi::class.java)




//    private var retrofit: Retrofit = getRetroIns()
//
//    companion object {
//        fun getRetroIns() : Retrofit {
//            var loggingInterceptor = HttpLoggingInterceptor()
//            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
//            var client = OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
//            return Builder()
//                .baseUrl("https://pokeapi.co/api/v2/")
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .addConverterFactory(GsonConverterFactory.create())
//                .client(client)
//                .build()
//        }
//    }
//
//    val api: NetworkApi = retrofit.create(NetworkApi::class.java)
}