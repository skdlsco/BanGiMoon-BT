package com.minsa.baingimoon_bt

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import app.akexorcist.bluetotohspp.library.BluetoothSPP
import app.akexorcist.bluetotohspp.library.BluetoothState
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by eka on 2018. 4. 1..
 */
class BluetoothService : Service() {
    private var bt: BluetoothSPP? = null
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (bt == null) {
            bt = BluetoothSPP(this).apply {
                setOnDataReceivedListener { data, message ->
                    Log.e("asdf123", message)
                    NetworkHelper.retrofitInstance.setDevices("37.488196", "127.064748").enqueue(object : Callback<ResponseBody> {
                        override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {

                        }

                        override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>?) {

                        }
                    })
                }
                setBluetoothStateListener { state ->
                    if (state == 3)
                        bt?.send("1", false)
                }
                setBluetoothConnectionListener(object : BluetoothSPP.BluetoothConnectionListener {
                    override fun onDeviceConnected(name: String, address: String) {}

                    override fun onDeviceDisconnected() {}

                    override fun onDeviceConnectionFailed() {}
                })
                setAutoConnectionListener(object : BluetoothSPP.AutoConnectionListener {
                    override fun onAutoConnectionStarted() {
                        Log.e("autoconnection", "bluetoothConnect");
                    }

                    override fun onNewConnection(name: String?, address: String?) {
                    }

                })
                setupService()
                startService(BluetoothState.DEVICE_OTHER)
                autoConnect("TEST")
            }
        }
        return START_STICKY
    }

    override fun onDestroy() {
        Log.e("destroy", "asfaf")
        bt?.stopService()
        super.onDestroy()
    }
}