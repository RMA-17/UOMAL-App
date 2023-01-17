package com.rmaprojects.uomal.ui.screen.home.event

import com.rmaprojects.uomal.data.remote.model.animelist.AnimeListResponse

sealed class HomeUiEvent {
    object Loading: HomeUiEvent()
    data class Success(val response: AnimeListResponse): HomeUiEvent()
    data class Error(val message: String): HomeUiEvent()
}