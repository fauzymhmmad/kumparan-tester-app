package com.test.kumparan.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.test.kumparan.R
import com.test.kumparan.ui.base.BaseActivity
import com.test.kumparan.ui.base.IPresenter
import com.test.kumparan.ui.main.post.PostActivity

class SplashScreen: BaseActivity<IPresenter>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this@SplashScreen, PostActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }, 1000)

    }
}