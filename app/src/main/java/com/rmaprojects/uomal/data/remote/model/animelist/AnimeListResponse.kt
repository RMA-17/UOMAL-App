package com.rmaprojects.uomal.data.remote.model.animelist


import com.google.gson.annotations.SerializedName

data class AnimeListResponse(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("links")
    val links: Links,
    @SerializedName("meta")
    val meta: Meta,
    @SerializedName("pagination")
    val pagination: Pagination
)