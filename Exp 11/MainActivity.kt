package com.example.ex11

import android.content.Context
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.TelephonyManager
import android.widget.Button
import android.widget.EditText
import androidx.core.app.ActivityCompat
class MainActivity : AppCompatActivity() {
    private val REQUEST_CODE_PHONE_STATE = 1000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val etNetworkOperatorName : EditText =
            findViewById(R.id.etNetworkOperatorName)
        val etPhoneType : EditText = findViewById(R.id.etPhoneType)
        val etNetworkCountryISO : EditText =
            findViewById(R.id.etNetworkCountryISO)
        val etSIMCountryISO : EditText = findViewById(R.id.etSIMCountryISO)
        val etDeviceSoftwareVersion : EditText =
            findViewById(R.id.etDeviceSoftwareVersion)
        val btGetTelephonyServices : Button =
            findViewById(R.id.btGetTelephonyServices)
        val telephonyManager = getSystemService(Context.TELEPHONY_SERVICE) as
                TelephonyManager
        if (ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.READ_PHONE_STATE) !=
            PackageManager.PERMISSION_GRANTED ) {
            ActivityCompat.requestPermissions(this,
                arrayOf(android.Manifest.permission.READ_PHONE_STATE), REQUEST_CODE_PHONE_STATE)
        }
        btGetTelephonyServices.setOnClickListener {
            val networkOperatorName = telephonyManager.networkOperatorName
            val phoneType: Int = telephonyManager.getPhoneType()
            var strphoneType : String = ""
            val networkCountryISO: String =
                telephonyManager.getNetworkCountryIso()
            val SIMCountryISO: String = telephonyManager.getSimCountryIso()
            val deviceSoftwareVersion: String? =
                telephonyManager.getDeviceSoftwareVersion()
            when (phoneType) {
                TelephonyManager.PHONE_TYPE_CDMA -> strphoneType = "CDMA"
                TelephonyManager.PHONE_TYPE_GSM -> strphoneType = "GSM"
                TelephonyManager.PHONE_TYPE_NONE -> strphoneType = "NONE"
            }
            etNetworkOperatorName.setText(networkOperatorName)
            etPhoneType.setText(strphoneType)
            etNetworkCountryISO.setText(networkCountryISO)
            etSIMCountryISO.setText(SIMCountryISO)
            etDeviceSoftwareVersion.setText(deviceSoftwareVersion)
        }
    }
}
