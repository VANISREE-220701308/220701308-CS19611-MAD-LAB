package com.example.ex9

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val sms : EditText = findViewById(R.id.edit_Text)
        val alert : Button = findViewById(R.id.alert_Button)
        val clear : Button = findViewById(R.id.clear_Button)
        val builder = AlertDialog.Builder(this)

        alert.setOnClickListener {
            if(sms.text.toString()!=""){
                builder
                    .setTitle("New Message")
                    .setMessage(sms.text.toString())
                    .setPositiveButton("ok"){ dialog, which ->
                        Toast.makeText(this,"Alert Dialog Closed",Toast.LENGTH_LONG).show()
                    }
                val smsIntent=Intent(this,SmsAlert::class.java)
                smsIntent.putExtra(sms.text.toString(),1)
                val alertBox = builder.create()
                alertBox.show()
            }
            else{
                Toast.makeText(this,"Type Message in Message Box",Toast.LENGTH_LONG).show()
            }
        }

        clear.setOnClickListener {
            sms.setText("")
        }

    }
}