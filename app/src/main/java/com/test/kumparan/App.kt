package com.test.kumparan

import android.util.Log
import androidx.multidex.MultiDexApplication
import com.squareup.picasso.Picasso
import com.test.kumparan.module.AppModule
import com.test.kumparan.module.PresenterModule
import roboguice.RoboGuice

class App : MultiDexApplication() {
    companion object {
        lateinit var app: App
    }

    override fun onCreate() {
        super.onCreate()
        app = this
        initPicasso()
        initRoboguice()
    }

    private fun initPicasso() {
        Picasso.setSingletonInstance(Picasso.Builder(this).listener { _, _, exception ->
            Log.d("Picasso", exception.message!!)
        }.build())
    }

    private fun initRoboguice() {
        RoboGuice.setUseAnnotationDatabases(false)
        RoboGuice.setupBaseApplicationInjector(this, AppModule(this), PresenterModule())
    }

}