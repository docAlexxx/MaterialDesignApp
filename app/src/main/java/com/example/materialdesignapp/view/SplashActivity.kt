package com.example.materialdesignapp.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.LinearInterpolator
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.materialdesignapp.R
import com.example.materialdesignapp.view.settings.curTheme

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(curTheme)
        setContentView(R.layout.activity_splash)


        findViewById<ImageView>(R.id.image_view).animate().rotationBy(1440f)
            .setInterpolator(LinearInterpolator()).duration = 10000L



        Handler(Looper.myLooper()!!).postDelayed({
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
        }, 3000L)

    }
}