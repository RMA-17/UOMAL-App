package com.rmaprojects.uomal.ui.screen.detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun DetailItems(
    animeName: String,
    imageUrl: String,
    animeDescription: String,
    onAir: String,
    nestedScrollConnection: NestedScrollConnection,
    modifier: Modifier = Modifier,
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .nestedScroll(nestedScrollConnection)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ){
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(165.dp)
                    .blur(24.dp),
                model = imageUrl,
                contentDescription = "$animeName Image",
                contentScale = ContentScale.Crop
            )
            AsyncImage(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(top = 24.dp)
                    .size(168.dp),
                model = imageUrl,
                contentDescription = "$animeName Image"
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Text(
                animeName,
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                animeDescription,
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Aired: $onAir",
                style = MaterialTheme.typography.titleSmall
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun DetailItemsPreview() {
    DetailItems(
        animeName = "Bleach - Sennen Kessen-hen",
        imageUrl = "",
        animeDescription = "Mamang ke mamang adalah mamang",
        onAir = "Now",
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior().nestedScrollConnection
    )
}