package com.example.happid

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telecom.Call
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import de.hdodenhof.circleimageview.CircleImageView
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class ProfileActivity : AppCompatActivity() {

    lateinit var title: TextView
    lateinit var circleImageView: CircleImageView
    lateinit var submitProfiledata: Button
    lateinit var firstName: EditText
    lateinit var lastName: EditText
    lateinit var phoneNum: EditText
    lateinit var postalcode: EditText
    private var profileImage: String = ""

    companion object {
         val baseUrl = "https://dev.pantheonsite.io/"
        val IMAGE_REQUEST_CODE = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        title = findViewById(R.id.title)
        title.text = "Create Profile"
        circleImageView = findViewById(R.id.profile_image)
        firstName = findViewById(R.id.firstname_edt_text)
        lastName = findViewById(R.id.lastname_edt_text)
        phoneNum = findViewById(R.id.phone_edt_text)
        postalcode = findViewById(R.id.postcode_edt_text)
        submitProfiledata = findViewById(R.id.btn_submit_profile)

        submitProfiledata.setOnClickListener {

            val userFName = firstName.text.toString()
            val userLName = lastName.text.toString()
            val contactNumber = phoneNum.text.toString()
            val postalCode = postalcode.text.toString()

            val retrofitBuilder = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
                .build()
                .create(ApiInterface::class.java)

            val jsonObject = JSONObject()

            jsonObject.put("userFName", userFName)
            jsonObject.put("userLName", userLName)
            jsonObject.put("contactNumber", contactNumber)
            jsonObject.put("postalCode", postalCode)
            jsonObject.put("profileImage", profileImage)

            val jsonObjectString = jsonObject.toString()

            val requestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())

            Log.e("request body", jsonObjectString)

            val createApiResponse = retrofitBuilder.createBusiness(requestBody)
            createApiResponse.enqueue(object : Callback<ApiResponse?> {
                override fun onResponse(
                    call: retrofit2.Call<ApiResponse?>,
                    response: Response<ApiResponse?>
                ) {
                    val items = response.body()
                    if (items != null) {
                        if (items.status == 1) {
                            val id: String = items.nid
                            Log.e("RETROFIT_Create_API", items.nid)
                            profileCreated()

                        }
                    } else {
                        Log.e("Create_API_inter", response.body()?.error.toString())
                    }
                }

                override fun onFailure(call: retrofit2.Call<ApiResponse?>, t: Throwable) {
                    t.localizedMessage?.let { it1 -> Log.e("RETROFIT_create_API", it1) }
                }

            })
        }
        circleImageView.setOnClickListener {
            selectImage()
        }
    }

    private fun profileCreated() {
        Toast.makeText(this,"Profile added", Toast.LENGTH_SHORT).show()
    }

    private fun selectImage() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == IMAGE_REQUEST_CODE && resultCode == RESULT_OK) {
            circleImageView.setImageURI(data?.data)
            profileImage = data.toString()
        }
    }

}