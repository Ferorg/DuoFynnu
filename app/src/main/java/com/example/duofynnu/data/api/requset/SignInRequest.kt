package com.example.duofynnu.data.api.requset

import com.google.gson.annotations.SerializedName

class SignInRequest (
    @SerializedName("login")
    val email: String,
    @SerializedName("password")
    val password: String,
)