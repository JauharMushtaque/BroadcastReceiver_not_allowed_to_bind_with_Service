package com.example.service.broadcastreceiver

import android.content.*
import android.os.IBinder
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MyBroadcastReceiver: BroadcastReceiver() {
private var context:Context?=null
    override fun onReceive(context: Context?, intent: Intent?) {
        this.context=context
        var intent = Intent(context, LocalService::class.java)
        context?.bindService(intent, serviceConnection, AppCompatActivity.BIND_AUTO_CREATE)
    }

    private val serviceConnection =  object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as LocalService.MyBinder
            Toast.makeText(context, binder.getService().sayHiToActivity(), Toast.LENGTH_LONG).show()
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            TODO("Not yet implemented")
        }
    }
}