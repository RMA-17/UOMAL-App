package com.rmaprojects.uomal.data.remote.model.anime


import com.google.gson.annotations.SerializedName

data class AnimeDetailResponse(
    @SerializedName("data")
    val `data`: Data
)