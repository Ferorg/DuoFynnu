package com.example.duofynnu.data.api.requset

import com.google.gson.annotations.SerializedName

class SignUpRequest (
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("username")
    val username: String,
)