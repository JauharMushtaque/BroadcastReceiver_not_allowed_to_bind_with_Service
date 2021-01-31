package com.example.service.broadcastreceiver

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder

class LocalService : Service() {
    inner class MyBinder : Binder() {
        fun getService(): LocalService = this@LocalService
    }

    private val mBinder: Binder = MyBinder()

    override fun onBind(intent: Intent): IBinder {
        return mBinder
    }

     fun sayHiToActivity():String{
        return "Hi MainActivity, this is from Service"
    }
}