package com.example.duofynnu.data.repository

import com.example.duofynnu.data.api.AuthApiService
import com.example.duofynnu.data.api.requset.SignInRequest
import com.example.duofynnu.data.api.requset.SignUpRequest
import com.example.duofynnu.data.api.util.doCall
import com.example.duofynnu.data.prefs.PrefsDataSource
import com.example.duofynnu.domain.entity.Session
import com.example.duofynnu.domain.entity.UserProfile
import com.example.duofynnu.domain.repository.SessionRepository
import com.example.duofynnu.domain.util.Event
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SessionRepositoryImpl @Inject constructor(
    private val prefsDataSource: PrefsDataSource,
    private val authApiService: AuthApiService,
) : SessionRepository {

    override suspend fun signIn(email: String, password: String): Event<Session> {
        val event = doCall {
            val request = SignInRequest(email, password)
            return@doCall authApiService.signIn(request)
        }

        return when (event) {
            is Event.Success -> {
                val response = event.data
                val session = Session(
                    token = response.token,
                    userProfile = UserProfile(
                        userId = response.id,
                        email = response.email,
                        username = response.username,
                    )
                )
                Event.Success(session)
            }

            is Event.Failure -> {
                val error = event.exception
                Event.Failure(error)
            }
        }
    }

    override suspend fun signUp(
        email: String, password: String, username: String
    ): Event<Session> {
        val event = doCall {
            val request = SignUpRequest(email = email, password = password, username = username)
            return@doCall authApiService.signUp(request)
        }

        return when (event) {
            is Event.Success -> {
                val response = event.data
                val session = Session(
                    token = "",
                    userProfile = UserProfile(
                        userId = response.id,
                        email = response.email,
                        username = response.username ?: "",
                    )
                )
                Event.Success(session)
            }

            is Event.Failure -> {
                val error = event.exception
                Event.Failure(error)
            }
        }
    }


    override suspend fun fetchToken(): Flow<String?> = flow {
        val token = prefsDataSource.fetchToken()
        emit(token)
    }

    override suspend fun saveToken(token: String) = prefsDataSource.saveToken(token)

    override suspend fun saveUserProfile(userProfile: UserProfile) =
        prefsDataSource.saveUserProfile(userProfile)
}