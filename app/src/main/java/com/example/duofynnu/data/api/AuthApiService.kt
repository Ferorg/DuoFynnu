package com.example.duofynnu.data.api

import com.example.duofynnu.data.api.requset.SignInRequest
import com.example.duofynnu.data.api.requset.SignUpRequest
import com.example.duofynnu.data.api.response.SignInResponse
import com.example.duofynnu.data.api.response.SignUpResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {
    @POST("users/login")
    suspend fun signIn(@Body request: SignInRequest): Response<SignInResponse>

    @POST("users/register")
    suspend fun signUp(@Body request: SignUpRequest): Response<SignUpResponse>
}