package com.example.sdcardex7

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.FileInputStream
import java.io.FileOutputStream

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val input =findViewById<EditText>(R.id.input)
        val read = findViewById<Button>(R.id.read)
        val save = findViewById<Button>(R.id.save)
        val file = "sdfile.txt"
        val clear = findViewById<Button>(R.id.clear)
        input!!.hint="Enter some text here"

        clear.setOnClickListener {
            input.setText("")
        }

        read.setOnClickListener {
            var c: Int
            var temp = ""
            try {
                val fis : FileInputStream = openFileInput(file)
                while ((fis.read().also { c=it})!=-1){
                    temp+=c.toChar().toString()
                }
                input.setText(temp)
                Toast.makeText(this,"File Read  : $file", Toast.LENGTH_LONG).show()
            }catch (e: Exception){
                Toast.makeText(this,e.message, Toast.LENGTH_LONG).show()

            }
        }

        save.setOnClickListener {
            val data = input.text.toString()
            try {
                val fos: FileOutputStream = openFileOutput(file,MODE_PRIVATE)
                fos.write(data.toByteArray())
                fos.close()
                Toast.makeText(this,"File Saved  : $file", Toast.LENGTH_LONG).show()
            }catch (e: Exception){
                Toast.makeText(this,e.message, Toast.LENGTH_LONG).show()
            }
        }

    }
}