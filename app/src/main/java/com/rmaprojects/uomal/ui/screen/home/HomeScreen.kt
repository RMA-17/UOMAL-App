package com.rmaprojects.uomal.ui.screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.rmaprojects.uomal.ui.components.AnimeCardItem
import com.rmaprojects.uomal.ui.screen.home.event.HomeUiEvent
import com.rmaprojects.uomal.utils.Converter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    goToDetail: (animeId: String) -> Unit,
    modifier: Modifier = Modifier,
    vIewModel: HomeVIewModel = hiltViewModel(),
) {
    Scaffold(
        modifier = Modifier,
        topBar = {
            CenterAlignedTopAppBar(
                scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(),
                title = { Text(text = "Home") },
            )
        }
    ) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = modifier.fillMaxSize()
                .padding(innerPadding),
        ) {
            vIewModel.eventFlow.collectAsState(initial = HomeUiEvent.Loading).let { state ->
                when (val event = state.value) {
                    is HomeUiEvent.Error -> {
                        Text(event.message)
                    }
                    is HomeUiEvent.Loading -> {
                        CircularProgressIndicator()
                    }
                    is HomeUiEvent.Success -> {
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(2),
                        ) {
                            val animeList = event.response.data
                            items(animeList.size) { index ->
                                val anime = animeList[index]
                                AnimeCardItem(
                                    animeRank = anime.rank,
                                    animeName = anime.title,
                                    animeRating = anime.score,
                                    animeAiredDate = Converter.dateConverter(
                                        anime.aired.prop.from.day,
                                        anime.aired.prop.from.month,
                                        anime.aired.prop.from.year
                                    ),
                                    animeType = anime.type,
                                    imageUrl = anime.images.jpg.imageUrl,
                                    onClicked = {
                                        goToDetail(anime.malId.toString())
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}