package com.example.ex6

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val uid = findViewById<EditText>(R.id.id)
        val uname =findViewById<EditText>(R.id.name)
        val validate = findViewById<Button>(R.id.validate)

        validate.setOnClickListener {
            val name = uname.text.toString()
            val id = uid.text.toString()
            if(name.matches(regex = Regex("[a-zA-Z]+"))&&
                id.matches(regex = Regex("[\\d]+"))&&
                id.length==4)
                Toast.makeText(applicationContext,"Validation Successful", Toast.LENGTH_LONG).show()
            else{
                if(name.length==0 || id.length==0)
                    Toast.makeText(applicationContext,"Please enter all values", Toast.LENGTH_LONG).show()
                if(!(name.matches(regex = Regex("[a-zA-Z]+"))))
                    Toast.makeText(applicationContext,"Please enter only enter alphabet", Toast.LENGTH_LONG).show()
                if(!(id.matches(regex = Regex("[\\d]+")))||id.length!=4)
                    Toast.makeText(applicationContext,"Please enter only four digit", Toast.LENGTH_LONG).show()

            }
        }

    }
}