package com.test.kumparan.api

import android.content.Context
import com.google.gson.Gson
import com.google.inject.Inject
import com.google.inject.Provider
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

class ApiContentProvider : Provider<ApiServiceInterface> {
    @Inject
    private lateinit var gson: Gson

    @Inject
    private lateinit var context : Context

    override fun get(): ApiServiceInterface {
        return Retrofit.Builder()
            .baseUrl(provideBaseUrl())
            .client(provideOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxErrorHandling.create())
            .build()
            .create(ApiServiceInterface::class.java)
    }

    private fun provideOkHttpClient(): OkHttpClient {
        val httpCacheDirectory = File(context.cacheDir, "responses")
        val cacheSize = 20 * 1024 * 1024 // 20 MiB
        val cache = Cache(httpCacheDirectory, cacheSize.toLong())

        val client = OkHttpClient.Builder()
        client.cache(cache)

        client.connectTimeout(30, TimeUnit.SECONDS)
        client.readTimeout(30, TimeUnit.SECONDS)

        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        client.addInterceptor(logging)

        client.hostnameVerifier { _, _ -> true }
        return client.build()
    }

    private fun provideBaseUrl(): String{
        return "https://jsonplaceholder.typicode.com/"
    }

}