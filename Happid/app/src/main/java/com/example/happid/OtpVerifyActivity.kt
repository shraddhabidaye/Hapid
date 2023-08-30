package com.example.happid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class OtpVerifyActivity : AppCompatActivity() {

    lateinit var receivedPhone: String
    lateinit var receivedOtp: String
    lateinit var displayPhone: TextView
    lateinit var otpEditTxt1: EditText
    lateinit var otpEditTxt2: EditText
    lateinit var otpEditTxt3: EditText
    lateinit var otpEditTxt4: EditText
    lateinit var btnSubmit: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp_verify)

        displayPhone = findViewById(R.id.text_otp_phone)
        otpEditTxt1 = findViewById(R.id.otpet1)
        otpEditTxt2 = findViewById(R.id.otpet2)
        otpEditTxt3 = findViewById(R.id.otpet3)
        btnSubmit = findViewById(R.id.btn_verify_otp)
        otpEditTxt4 = findViewById(R.id.otpet4)
        receivedPhone = intent.getStringExtra("phoneNumber").toString()
        receivedOtp = intent.getStringExtra("otpNumber").toString()
        displayPhone.text = receivedPhone

        btnSubmit.setOnClickListener {
            val enteredOtp: String = otpEditTxt1.text.toString() + otpEditTxt2.text.toString() + otpEditTxt3.text.toString() +otpEditTxt4.text.toString()
            if(receivedOtp == enteredOtp){
                Toast.makeText(this,"OTP Verified",Toast.LENGTH_SHORT).show()
                val intent = Intent(this, ProfileActivity::class.java)
                startActivity(intent)
                finish()
            }
            else {

                Toast.makeText(this,"OTP Didn't Match.Please re-enter",Toast.LENGTH_SHORT).show()

            }
        }
    }
}