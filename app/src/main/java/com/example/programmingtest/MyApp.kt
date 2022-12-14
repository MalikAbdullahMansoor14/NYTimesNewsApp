package com.example.programmingtest

/**
 * @author Abdullah Mansoor
 * @Date 8/13/22
 */


import android.app.Application

class MyApp : Application() {

    companion object {
        lateinit var instance: MyApp
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

}