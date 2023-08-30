package com.example.happid

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity

class OtpDisplay(context: Context, otp: String) : Dialog(context) {

    lateinit var otpText1 : TextView
    lateinit var otpText2 : TextView
    lateinit  var otpText3 : TextView
    lateinit var otpText4 : TextView
    val otpcorrect:String = otp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_otp)

        otpText1 = findViewById(R.id.otptv1)
        otpText2 = findViewById(R.id.otptv2)
        otpText3 = findViewById(R.id.otptv3)
        otpText4 = findViewById(R.id.otptv4)

        otpText1.setText(otpcorrect[0].toString())
        otpText2.setText(otpcorrect[1].toString())
        otpText3.setText(otpcorrect[2].toString())
        otpText4.setText(otpcorrect[3].toString())

    }
}