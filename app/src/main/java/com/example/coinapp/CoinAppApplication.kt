package com.example.coinapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CoinAppApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}