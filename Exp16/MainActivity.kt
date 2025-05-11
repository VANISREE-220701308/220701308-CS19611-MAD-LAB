package com.example.ex16image

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
class MainActivity : AppCompatActivity() {
    lateinit var imgImage : ImageView
    private val REQUEST_CODE_IMAGE_CAPTURE = 1000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        imgImage = findViewById(R.id.imgImage)
        val btTakePicture : Button = findViewById(R.id.btTakePicture)
        btTakePicture.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent, REQUEST_CODE_IMAGE_CAPTURE)
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data:
    Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == REQUEST_CODE_IMAGE_CAPTURE && resultCode == RESULT_OK)
        {
            val photo = data!!.extras!!["data"] as Bitmap?
            imgImage.setImageBitmap(photo)
        }
    }
}