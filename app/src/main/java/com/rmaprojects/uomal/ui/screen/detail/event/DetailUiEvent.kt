package com.rmaprojects.uomal.ui.screen.detail.event

import com.rmaprojects.uomal.data.remote.model.anime.AnimeDetailResponse

sealed class DetailUiEvent {
    object Loading : DetailUiEvent()
    data class Success(val response: AnimeDetailResponse) : DetailUiEvent()
    data class Error(val message: String) : DetailUiEvent()
}