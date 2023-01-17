package com.rmaprojects.uomal.ui.screen.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.rmaprojects.uomal.ui.screen.detail.event.DetailUiEvent
import com.rmaprojects.uomal.utils.getAnimeAiringDate
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel = hiltViewModel()
) {
    val scope = rememberCoroutineScope()
    val snackbarHostState = SnackbarHostState()
    var animeName by remember { mutableStateOf("Anime Detail") }
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    Scaffold(
        topBar = {
            MediumTopAppBar(
                modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
                title = {
                    Text(
                        text = animeName,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navigateBack() }) {
                        Icon(Icons.Default.ArrowBack, null)
                    }
                },
                scrollBehavior = scrollBehavior
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center
        ) {
            viewModel.eventFlow.collectAsState(initial = DetailUiEvent.Loading).let { state ->
                when (val event = state.value) {
                    is DetailUiEvent.Error -> {
                        scope.launch { snackbarHostState.showSnackbar(event.message) }
                    }
                    is DetailUiEvent.Loading -> {
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                    }
                    is DetailUiEvent.Success -> {
                        val animeData = event.response.data
                        animeName = animeData.titleSynonyms.first()
                        DetailItems(
                            animeName = animeData.title,
                            animeDescription = animeData.synopsis,
                            imageUrl = animeData.images.jpg.imageUrl,
                            onAir = getAnimeAiringDate(animeData.aired.prop),
                            nestedScrollConnection = scrollBehavior.nestedScrollConnection
                        )
                    }
                }

            }
        }
    }
}


@Preview
@Composable
fun DetailScreenPreview() {
    DetailScreen({})
}