package com.test.kumparan.module

import android.app.Application
import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.inject.AbstractModule
import com.test.kumparan.api.ApiContentProvider
import com.test.kumparan.api.ApiServiceInterface
import com.test.kumparan.api.BooleanTypeAdapter

class AppModule  (private val application: Application): AbstractModule(){
    override fun configure() {
        val gson = GsonBuilder().registerTypeAdapter(Boolean::class.javaPrimitiveType, BooleanTypeAdapter()).create()
        bind(Gson::class.java).toInstance(gson)
        bind(Context::class.java).toInstance(application)
        bind(ApiServiceInterface::class.java).toProvider(ApiContentProvider::class.java).asEagerSingleton()
    }
}