package com.test.kumparan.guice

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.inject.Injector
import roboguice.RoboGuice

abstract class RoboAppCompatActivity : AppCompatActivity(){
    protected val injector: Injector by lazy {
        RoboGuice.getInjector(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injector.injectMembers(this)
    }

    override fun onDestroy() {
        try {
            RoboGuice.destroyInjector(this)
        }finally {
            super.onDestroy()
        }
    }
}