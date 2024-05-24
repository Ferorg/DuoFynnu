package com.example.duofynnu.data.repository

import com.example.duofynnu.data.prefs.PrefsDataSource
import com.example.duofynnu.domain.entity.UserProfile
import com.example.duofynnu.domain.entity.isValid
import com.example.duofynnu.domain.repository.UserRepository
import com.example.duofynnu.domain.util.Event
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val prefsDataSource: PrefsDataSource,
) : UserRepository {
    override suspend fun fetchUserProfile(): Event<UserProfile> {
        val userProfile = prefsDataSource.fetchUserProfile()
        return if (userProfile.isValid()) {
            Event.Failure("User is not valid")
        } else {
            Event.Success(userProfile)
        }
    }


}