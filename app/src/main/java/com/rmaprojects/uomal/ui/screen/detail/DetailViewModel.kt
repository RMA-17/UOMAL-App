package com.rmaprojects.uomal.ui.screen.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rmaprojects.uomal.data.repository.UOMALRepository
import com.rmaprojects.uomal.ui.screen.detail.event.DetailUiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: UOMALRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _eventFlow = MutableSharedFlow<DetailUiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()
    private val animeId = savedStateHandle.get<String>("animeId")

    init {
        viewModelScope.launch {
            getAnimeDetail(animeId)
        }
    }

    private suspend fun getAnimeDetail(animeId: String?) {
        _eventFlow.emit(DetailUiEvent.Loading)
        try {
            if (animeId.isNullOrEmpty()) {
                _eventFlow.emit(DetailUiEvent.Error("No MAL ID Given cannot show Anime detail"))
                return
            }
            val response = repository.getAnimeDetail(animeId)
            if (response == null) {
                _eventFlow.emit(DetailUiEvent.Error("Currently, the Anime is not on the database"))
                return
            }
            _eventFlow.emit(DetailUiEvent.Success(response))
        } catch (e: Exception) {
            _eventFlow.emit(DetailUiEvent.Error(e.message ?: "Error fetching anime detail"))
        }
    }
}