package com.example.duofynnu.di

import com.example.duofynnu.data.repository.UserRepositoryImpl
import com.example.duofynnu.data.repository.SessionRepositoryImpl
import com.example.duofynnu.domain.repository.SessionRepository
import com.example.duofynnu.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface RepositoryBindModule {
    @Binds
    fun bindSessionRepository(sessionRepositoryImpl: SessionRepositoryImpl): SessionRepository

    @Binds
    fun bindUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository
}