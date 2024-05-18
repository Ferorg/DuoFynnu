package com.example.duofynnu.presentation.splash

sealed interface SplashViewState {
    data object Success : SplashViewState
    data object Failure : SplashViewState
    data object Loading : SplashViewState
}