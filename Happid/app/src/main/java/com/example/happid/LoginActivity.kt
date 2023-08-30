package com.example.happid

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.hbb20.CountryCodePicker

class LoginActivity : AppCompatActivity() {
    private lateinit var country_code: CountryCodePicker
    private lateinit var phoneInput: EditText
    private lateinit var sendOtpBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val backBtn : ImageView = findViewById(R.id.home)
        country_code = findViewById(R.id.ccp_text)
        phoneInput = findViewById(R.id.phone_number)
        sendOtpBtn = findViewById(R.id.btn_send_otp)

        country_code.setNumberAutoFormattingEnabled(false)
        country_code.registerCarrierNumberEditText(phoneInput)

        sendOtpBtn.setOnClickListener {
            if(!country_code.isValidFullNumber) {
                phoneInput.setError("Phone number not valid")
                return@setOnClickListener
            }
            else {
                val phoneNumber: String = phoneInput.text.toString()
                val otp = StringBuilder("")
                if (phoneNumber != null) {
                    otp.append(phoneNumber.substring(0, 2))
                    otp.append(phoneNumber.substring(phoneNumber.length - 2, phoneNumber.length))
                }

                val otpDisplay = OtpDisplay(this,otp.toString())
                otpDisplay.setCancelable(false)
                otpDisplay.show()
                Handler().postDelayed({
                    val intent = Intent(this, OtpVerifyActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NO_ANIMATION
                    intent.putExtra("phoneNumber", phoneNumber)
                    intent.putExtra("otpNumber", otp.toString())
                    startActivity(intent)
                    finish()
                },3000)
            }

        }

        backBtn.setOnClickListener {
            val intent = Intent(this, SplashIntroActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NO_ANIMATION
            startActivity(intent)
            finish()
        }
    }
}