package com.mohsin.threesidedcubetest.network

import com.clarity.android.interview.network.NetworkApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.Retrofit.Builder
import retrofit2.converter.gson.GsonConverterFactory

class NetworkService {
    // providing the base url for a network call
    private val retrofit: Retrofit = Builder()
        .baseUrl("https://pokeapi.co/api/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    // networkApi object to handle all api calls
    val api: NetworkApi = retrofit.create(NetworkApi::class.java)



//    we can use the code below if we want to have a look into http logging for api call
//    private var retrofit: Retrofit = getRetroIns()
//
//    companion object {
//        fun getRetroIns() : Retrofit {
//            var loggingInterceptor = HttpLoggingInterceptor()
//            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
//            var client = OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
//            return Builder()
//                .baseUrl("https://pokeapi.co/api/v2/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .client(client)
//                .build()
//        }
//    }
//
//    val api: NetworkApi = retrofit.create(NetworkApi::class.java)

}