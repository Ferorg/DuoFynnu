package com.example.duofynnu.presentation.homepage.rooms

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.duofynnu.domain.entity.Room
import com.example.duofynnu.presentation.util.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class RoomsViewModel @Inject constructor(
    context: Application,
) : AndroidViewModel(context) {
    private val _viewState = MutableStateFlow<ViewState<List<Room>>>(ViewState.Loading)
    val viewState: StateFlow<ViewState<List<Room>>> = _viewState
    private lateinit var idRoomsString: String
}