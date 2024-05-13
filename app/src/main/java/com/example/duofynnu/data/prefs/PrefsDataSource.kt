package com.example.duofynnu.data.prefs

import android.content.Context
import com.example.duofynnu.domain.entity.UserProfile
import javax.inject.Inject

class PrefsDataSourceImpl @Inject constructor(private val context: Context) : PrefsDataSource {

    override fun saveToken(token: String) {
        val prefs = context.getSharedPreferences(
            sessionPrefs, Context.MODE_PRIVATE
        )

        with(prefs.edit()) {
            putString(tokenKey, token)
            apply()
        }
    }

    override fun fetchToken(): String? {
        val prefs = context.getSharedPreferences(
            sessionPrefs, Context.MODE_PRIVATE
        )

        return prefs.getString(tokenKey, "")
    }

    override fun saveUserProfile(userProfile: UserProfile) {
        val prefs = context.getSharedPreferences(
            sessionPrefs, Context.MODE_PRIVATE
        )

        with(prefs.edit()) {
            putString(userIdKey, userProfile.userId)
            putString(emailKey, userProfile.email)
            putString(usernameKey, userProfile.username)
            apply()
        }

    }

    override fun fetchUserProfile(): UserProfile {
        val prefs = context.getSharedPreferences(
            sessionPrefs, Context.MODE_PRIVATE
        )
        return UserProfile(
            userId = prefs.getString(userIdKey, "") ?: "",
            email = prefs.getString(emailKey, "") ?: "",
            username = prefs.getString(usernameKey, "") ?: "",
        )
    }

    companion object {
        const val sessionPrefs = "session_prefs"
        const val tokenKey = "token_key"
        const val userIdKey = "current_user_id"
        const val usernameKey = "current_username"
        const val emailKey = "current_user_email"
    }
}

interface PrefsDataSource {
    fun saveToken(token: String)
    fun fetchToken(): String?
    fun saveUserProfile(userProfile: UserProfile)
    fun fetchUserProfile(): UserProfile
}