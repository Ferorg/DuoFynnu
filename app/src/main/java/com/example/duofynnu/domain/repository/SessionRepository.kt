package com.example.duofynnu.domain.repository

import com.example.duofynnu.domain.entity.Session
import com.example.duofynnu.domain.entity.UserProfile
import com.example.duofynnu.domain.util.Event
import kotlinx.coroutines.flow.Flow

interface SessionRepository {

    suspend fun signIn(email: String, password: String): Event<Session>
    suspend fun signUp(email: String, password: String, username: String): Event<Session>

    suspend fun fetchToken(): Flow<String?>

    suspend fun saveToken(token: String)

    suspend fun saveUserProfile(userProfile: UserProfile)
}