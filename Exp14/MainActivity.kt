package com.example.ex14

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.util.*
class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener {
    lateinit var tts : TextToSpeech
    lateinit var btSpeak : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val etText : EditText = findViewById(R.id.etText)
        btSpeak = findViewById(R.id.btSpeak)
        btSpeak.isEnabled = false
        tts = TextToSpeech(this, this)
        btSpeak.setOnClickListener {
            val text = etText!!.text.toString()
            tts!!.speak(text, TextToSpeech.QUEUE_FLUSH, null,"")
        }
    }
    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = tts!!.setLanguage(Locale.US)
            if (result == TextToSpeech.LANG_MISSING_DATA || result ==
                TextToSpeech.LANG_NOT_SUPPORTED) {
                Toast.makeText(applicationContext, "The Language not supported...!", Toast.LENGTH_LONG).show()
            }
            else {
                btSpeak!!.isEnabled = true
            }
        }
    }
}