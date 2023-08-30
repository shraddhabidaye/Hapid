package com.example.happid

data class ApiResponse(
    val nid: String,
    val status: Int,
    val error: Int,
    val message: String
)
