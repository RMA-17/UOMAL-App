package com.rmaprojects.uomal.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rmaprojects.uomal.data.repository.UOMALRepository
import com.rmaprojects.uomal.ui.screen.home.event.HomeUiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeVIewModel @Inject constructor(
    private val repository: UOMALRepository
): ViewModel() {
    private val _eventFlow = MutableSharedFlow<HomeUiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun getPopularAnimeList() {
        viewModelScope.launch {
            _eventFlow.emit(HomeUiEvent.Loading)
            try {
                val response = repository.getPopularAnimeList()
                _eventFlow.emit(HomeUiEvent.Success(response))
            } catch (e: Exception) {
                _eventFlow.emit(HomeUiEvent.Error(e.message ?: "Error when retrieving data"))
            }
        }
    }
}