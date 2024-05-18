package com.example.duofynnu.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.duofynnu.domain.usecase.FetchSessionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val fetchSessionUseCase: FetchSessionUseCase,
) : ViewModel() {
    private val _viewState = MutableStateFlow<SplashViewState>(SplashViewState.Loading)
    val viewState: StateFlow<SplashViewState> = _viewState
    init {
        verifySession()
    }
    private fun verifySession() {
        viewModelScope.launch {
            fetchSessionUseCase()
                .onStart { delay(twoSeconds) }
                .catch {
                    _viewState.value =
                        SplashViewState.Failure
                }
                .collect { token ->
                    _viewState.value = if (token.isNullOrEmpty()) {
                        SplashViewState.Failure
                    } else {
                        fetchSessionUseCase()
                        SplashViewState.Success
                    }
                }
        }
    }
    companion object {
        val twoSeconds: Duration = 2.seconds
    }
}