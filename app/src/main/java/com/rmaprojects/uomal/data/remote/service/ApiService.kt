package com.rmaprojects.uomal.data.remote.service

import com.rmaprojects.uomal.data.remote.model.AnimeListResponse
import com.rmaprojects.uomal.data.remote.model.Data
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("top/anime")
    suspend fun getTopAnime(
        @Query("filter") filter: String = "",
    ) : AnimeListResponse

    @GET("top/anime")
    suspend fun getTopAnime(
        @Query("page") page: Int,
        @Query("filter") filter: String = "",
    ) : AnimeListResponse

    @GET("anime/{animeId}")
    suspend fun getAnimeDetail(
        @Path("animeId") id: Int
    ): Data
}