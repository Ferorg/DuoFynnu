package com.example.duofynnu.presentation.sign.up

interface SignUpViewState {

    data object Success : SignUpViewState
    data class Failure(val message: String) : SignUpViewState
    data object Loading : SignUpViewState
    data object Idle : SignUpViewState
}