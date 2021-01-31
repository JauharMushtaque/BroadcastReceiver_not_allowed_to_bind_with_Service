package com.example.service.broadcastreceiver

import android.content.ComponentName
import android.content.Intent
import android.content.IntentFilter
import android.content.ServiceConnection
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private val serviceConnection =  object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as LocalService.MyBinder
            Toast.makeText(this@MainActivity, binder.getService().sayHiToActivity(), Toast.LENGTH_LONG).show()
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            TODO("Not yet implemented")
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews():Unit{
        findViewById<Button>(R.id.start_broadcast).setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?){
                intent= Intent(this@MainActivity, MyBroadcastReceiver::class.java)
                var intentFilter = IntentFilter()
                registerReceiver(MyBroadcastReceiver(),intentFilter)
                sendBroadcast(intent)
            }
        })
    }

    override fun onStop(){
        super.onStop()
        unbindService(serviceConnection)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MainActivity", "onDestroy: called")
    }

}