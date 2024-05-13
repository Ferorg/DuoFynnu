package com.example.duofynnu.data.api.response

import com.google.gson.annotations.SerializedName

class SignUpResponse (
    @SerializedName("objectId")
    val id: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("username")
    val username: String?,
)