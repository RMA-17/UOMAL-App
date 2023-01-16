package com.rmaprojects.uomal.ui.screen.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rmaprojects.uomal.data.remote.model.Prop
import com.rmaprojects.uomal.ui.components.AnimeCardItem
import com.rmaprojects.uomal.ui.screen.home.event.HomeUiEvent
import com.rmaprojects.uomal.utils.Converter
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    goToDetail: (animeId: String) -> Unit,
    modifier: Modifier = Modifier,
    vIewModel: HomeVIewModel = hiltViewModel(),
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier,
        topBar = {
            CenterAlignedTopAppBar(
                scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(),
                title = { Text(text = "Home") },
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            vIewModel.eventFlow.collectAsState(initial = HomeUiEvent.Loading).let { state ->
                when (val event = state.value) {
                    is HomeUiEvent.Error -> {
                        scope.launch {
                            snackbarHostState.showSnackbar(
                                event.message
                            )
                        }
                        Text(
                            "Error loading data, retry?",
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Button(onClick = { vIewModel.getPopularAnimeList() }) {
                            Text(text = "Retry")
                        }
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
                                    animeAiredDate = getAnimeAiringDate(anime.aired.prop),
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

private fun getAnimeAiringDate(prop: Prop) : String {
    val animeFrom = Converter.dateConverter(
        prop.from.day,
        prop.from.month,
        prop.from.year
    )
    val animeTo = Converter.dateConverter(
        prop.to.day,
        prop.to.month,
        prop.to.year
    )
    return "$animeFrom to $animeTo"
}