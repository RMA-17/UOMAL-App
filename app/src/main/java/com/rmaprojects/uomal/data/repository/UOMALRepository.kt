package com.rmaprojects.uomal.data.repository

import com.rmaprojects.uomal.data.remote.model.anime.AnimeDetailResponse
import com.rmaprojects.uomal.data.remote.model.animelist.AnimeListResponse
import com.rmaprojects.uomal.data.remote.service.ApiService
import com.rmaprojects.uomal.utils.AnimeFilter
import javax.inject.Inject

class UOMALRepository @Inject constructor(
    private val api: ApiService
) {
    suspend fun getPopularAnimeList() : AnimeListResponse = api.getTopAnime()
    suspend fun filterPopularAnimeListBy(filter: AnimeFilter): AnimeListResponse = api.getTopAnime(filter.value)
    suspend fun getAnimeDetail(animeId: String): AnimeDetailResponse? = api.getAnimeDetail(animeId.toInt())
}