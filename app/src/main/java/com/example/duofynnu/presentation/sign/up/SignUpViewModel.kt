package com.example.duofynnu.presentation.sign.up

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.duofynnu.domain.usecase.SignInUseCase
import com.example.duofynnu.domain.usecase.SignUpUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

class SignUpViewModel @Inject constructor(
    context: Application,
    private val signUpUseCase: SignUpUseCase,
    private val signInUseCase: SignInUseCase
) : AndroidViewModel(context) {
    private val _viewState = MutableStateFlow<SignUpViewState>(SignUpViewState.Idle)
    val viewState = _viewState.asStateFlow()
    fun onSignUpButtonClicked(email: String, password: String, username: String) {
        viewModelScope.launch {
            signUpUseCase(SignUpUseCase.Params(email, password, username))
                .onStart { _viewState.value = SignUpViewState.Loading }
                .catch {
                    _viewState.value =
                        SignUpViewState.Failure(it.message ?: "Something went wrong.")
                }
                .collect { _ ->
                    signIn(email, password)
                }
        }
    }
    private suspend fun signIn(email: String, password: String) {
        viewModelScope.launch {
            signInUseCase(SignInUseCase.Params(email, password))
                .onStart { _viewState.value = SignUpViewState.Loading }
                .catch {
                    _viewState.value =
                        SignUpViewState.Failure(it.message ?: "Something went wrong.")
                }
                .collect { _ ->
                    _viewState.value = SignUpViewState.Success
                }
        }
    }
}