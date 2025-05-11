package com.example.ex15speechtotext

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognizerIntent
import android.widget.ImageView
import android.widget.TextView
import java.util.*
class MainActivity : AppCompatActivity() {
    lateinit var tvText : TextView
    private val REQUEST_CODE_SPEECH_INPUT = 1000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var imgMic : ImageView = findViewById(R.id.imgMic)
        tvText = findViewById(R.id.tvText)
        imgMic.setOnClickListener {
            val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,
                Locale.getDefault())
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak...!")
            startActivityForResult(intent, REQUEST_CODE_SPEECH_INPUT)
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data:
    Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == REQUEST_CODE_SPEECH_INPUT && resultCode == RESULT_OK
            && data != null) {
            var res : ArrayList<String> =
                data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS) as
                        ArrayList<String>
            tvText.setText( Objects.requireNonNull(res)[0])
        }
    }
}