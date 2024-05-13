package com.example.duofynnu.data.api.response

import com.google.gson.annotations.SerializedName

class SignInResponse (
    @SerializedName("objectId")
    val id: String,
    @SerializedName("user-token")
    val token: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("username")
    val username: String,
)