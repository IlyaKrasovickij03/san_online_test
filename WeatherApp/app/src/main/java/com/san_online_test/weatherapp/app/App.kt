package com.san_online_test.weatherapp.app

import android.app.Application
import com.san_online_test.weatherapp.di.AppModule
import com.san_online_test.weatherapp.di.DaggerAppComponent
import com.san_online_test.weatherapp.di.DiProvider

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        setupDagger()
    }


    private fun setupDagger(){
        val appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(application = this, context = applicationContext))
            .build()
        DiProvider.appComponent = appComponent
    }
}