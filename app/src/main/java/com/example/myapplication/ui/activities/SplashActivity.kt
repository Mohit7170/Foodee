package com.example.myapplication.ui.activities

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.postDelayed
import com.example.myapplication.R
import com.example.myapplication.modals.DemoMenuItems
import com.example.myapplication.uttils.Params
import com.example.myapplication.uttils.SharedPrefHandler
import java.util.Locale

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val appLan = SharedPrefHandler(this).getAppLanguageKey()

        val locale = Locale(appLan)
        Locale.setDefault(locale)
        val config = Configuration()
        config.locale = locale
        resources.updateConfiguration(config, resources.displayMetrics)

        DemoMenuItems.initData(this)

        Handler(Looper.getMainLooper()).postDelayed(2000) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}