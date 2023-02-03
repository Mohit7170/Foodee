package com.example.myapplication.uttils

import android.app.Activity
import android.view.View
import android.view.WindowManager
import androidx.core.content.ContextCompat

class HelperClass {

    companion object{
        fun changeStatusBarColor(activity: Activity, color: Int, isLight: Boolean) {
            val window = activity.window
            val view = window.decorView
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = ContextCompat.getColor(activity, color)
            if (isLight) window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR else window.decorView.systemUiVisibility =
                view.systemUiVisibility and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
        }


    }
}