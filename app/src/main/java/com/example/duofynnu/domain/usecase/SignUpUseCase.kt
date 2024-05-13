package com.example.duofynnu.domain.usecase

import com.example.duofynnu.domain.entity.Session
import com.example.duofynnu.domain.repository.SessionRepository
import com.example.duofynnu.domain.util.Event
import com.example.duofynnu.domain.util.UseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SignUpUseCase @Inject constructor(private val sessionRepository: SessionRepository) :
    UseCase<SignUpUseCase.Params, Session> {
    data class Params(
        val email: String,
        val password: String,
        val username: String,
    )

    override suspend fun invoke(params: Params): Flow<Session> = flow{
        val email=params.email
        val password=params.password
        val username=params.username
        if(email.isNotEmpty() && password.isNotEmpty() && username.isNotEmpty()){
            val event= sessionRepository.signUp(email=email,password=password,username=username)
            when(event){
                is Event.Success->{
                    val session=event.data
                    sessionRepository.saveToken(session.token)
                    sessionRepository.saveUserProfile(session.userProfile)
                    emit(session)
                }
                is Event.Failure->{
                    throw Exception(event.exception)
                }
            }
        }else{
            throw Exception("Email/password/name is empty")
        }

    }
}