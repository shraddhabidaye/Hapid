package com.example.happid

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiInterface {
    @Headers("api-key: 111")
    @POST("v1/api/business/add")
    fun createBusiness(@Body requestBody: okhttp3.RequestBody): Call<ApiResponse>

}