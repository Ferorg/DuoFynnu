package com.example.duofynnu.domain.repository

import com.example.duofynnu.domain.entity.UserProfile
import com.example.duofynnu.domain.util.Event

interface UserRepository {
    suspend fun fetchUserProfile(): Event<UserProfile>
}