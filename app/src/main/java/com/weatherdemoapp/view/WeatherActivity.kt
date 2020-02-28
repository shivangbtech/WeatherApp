package com.weatherdemoapp.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.weatherdemoapp.R
import com.weatherdemoapp.navigation.TransitionManager

class WeatherActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)

        TransitionManager.replaceFragment(
            this,
            WeatherFragment(),
            R.id.fl_container_todo,
            false,
            TransitionManager.TransitionAnimation.TRANSITION_NONE
        )
    }
}
