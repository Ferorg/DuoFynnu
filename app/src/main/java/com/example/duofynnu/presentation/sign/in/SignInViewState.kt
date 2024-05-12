package com.example.duofynnu.presentation.sign.`in`

sealed interface SignInViewState {
    data object Success : SignInViewState
    data class Failure(val message: String) : SignInViewState
    data object Loading : SignInViewState
    data object Idle : SignInViewState
}