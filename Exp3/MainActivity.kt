package com.example.drawexp3

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val t1=findViewById<TouchScreen>(R.id.t1)
        val b1=findViewById<Button>(R.id.b1)
        val b2=findViewById<Button>(R.id.b2)
        val b3=findViewById<Button>(R.id.b3)
        val b4=findViewById<Button>(R.id.b4)
        b1.setOnClickListener{
            t1.setDrawint(0)
        }
        b2.setOnClickListener{
            t1.setDrawint(1)
        }
        b3.setOnClickListener{
            t1.setDrawint(2)
        }
        b4.setOnClickListener{
            t1.setDrawint(4)
        }

    }
}