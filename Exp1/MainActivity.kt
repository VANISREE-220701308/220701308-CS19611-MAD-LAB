package com.example.myapplicationex1

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val  textView  = findViewById<TextView>(R.id.t1)
        val button1 = findViewById<Button>(R.id.size)
        val button2 = findViewById<Button>(R.id.font)
        val button3 = findViewById<Button>(R.id.color)

        var fontidx = 0
        val ffont = arrayOf(Typeface.DEFAULT,Typeface.SERIF,Typeface.SANS_SERIF,Typeface.MONOSPACE)
        var fontsize = 0
        val fsize = arrayOf(16f,18f,20f,22f,24f,28f)
        var fontcolor = 0
        val fcolor = arrayOf(Color.BLUE,Color.BLACK,Color.RED,Color.YELLOW)

        button1.setOnClickListener{
            fontsize = (fontsize + 1) % fsize.size
            textView.textSize = fsize[fontsize]
            Toast.makeText(this,"font size changed ",Toast.LENGTH_SHORT).show()
        }
        button2.setOnClickListener{
            fontidx = (fontidx + 1) % ffont.size
            textView.typeface = ffont[fontidx]
            Toast.makeText(this,"font has changed ",Toast.LENGTH_SHORT).show()
        }
        button3.setOnClickListener{
            fontcolor = (fontcolor + 1) % fcolor.size
            textView.setTextColor(fcolor[fontcolor])
            Toast.makeText(this,"font color changed ",Toast.LENGTH_SHORT).show()
        }
    }
}