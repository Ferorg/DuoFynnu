package com.example.duofynnu.domain.usecase

import android.provider.ContactsContract.CommonDataKinds.Email
import com.example.duofynnu.domain.entity.Session
import com.example.duofynnu.domain.repository.SessionRepository
import com.example.duofynnu.domain.util.Event
import com.example.duofynnu.domain.util.UseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SignInUseCase @Inject constructor(private val sessionRepository: SessionRepository):
    UseCase<SignInUseCase.Params, Session> {
        data class Params(
            val email: String,
            val password:String,
        )

    override suspend fun invoke(params: Params): Flow<Session> = flow {
        val email=params.email
        val password=params.password
        if(email.isNotEmpty() && password.isNotEmpty()){
            val event=
                sessionRepository.signIn(email=email,password=password)
            when(event){
                is Event.Success->{
                    val session= event.data
                    sessionRepository.saveToken(session.token)
                    sessionRepository.saveUserProfile(session.userProfile)
                    emit(session)
                }
                is Event.Failure->{
                    throw Exception(event.exception)
                }
            }
        } else{
            throw Exception("Email or password is wrong")
        }
    }
}