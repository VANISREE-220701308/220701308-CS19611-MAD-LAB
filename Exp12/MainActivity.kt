package com.example.ex12

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.SmsManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.app.ActivityCompat
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val etPhoneNumber : EditText = findViewById(R.id.etPhoneNumber)
        val etMessage : EditText = findViewById(R.id.etMessage)
        val btSend : Button = findViewById(R.id.btSend)
        ActivityCompat.requestPermissions(this,
            arrayOf(android.Manifest.permission.SEND_SMS),1000)
        btSend.setOnClickListener {
            val phoneNumber = etPhoneNumber.text.toString()
            val message = etMessage.text.toString()
            val smsManager: SmsManager
            smsManager = SmsManager.getDefault()
            smsManager.sendTextMessage(phoneNumber, null, message, null, null)
            Toast.makeText(applicationContext, "Message Sent",
                Toast.LENGTH_LONG).show()
        }
    }
}
