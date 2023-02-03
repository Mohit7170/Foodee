package com.example.myapplication.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.core.os.postDelayed
import com.example.myapplication.R
import com.example.myapplication.modals.DemoMenuItems

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        DemoMenuItems.initData(this)

        Handler(Looper.getMainLooper()).postDelayed(2000) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}