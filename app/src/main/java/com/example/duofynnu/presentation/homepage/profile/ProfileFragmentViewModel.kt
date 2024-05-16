package com.example.duofynnu.presentation.homepage.profile

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.duofynnu.domain.usecase.SignInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class ProfileFragmentViewModel@Inject constructor(
    context: Application, private val signInUseCase: SignInUseCase
) : AndroidViewModel(context) {
}