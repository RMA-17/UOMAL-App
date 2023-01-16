package com.rmaprojects.uomal.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnimeCardItem(
    animeRank: Int,
    animeName: String,
    animeRating: Double,
    animeAiredDate: String,
    animeType: String,
    imageUrl: String,
    onClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .height(400.dp),
        onClick = { onClicked() }
    ) {
        Box(
            modifier = Modifier.height(250.dp)
        ) {
            AsyncImage(
                model = imageUrl,
                contentDescription = "$animeName Poster",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(8.dp)
                    .background(
                        color = MaterialTheme.colorScheme.primary,
                        shape = CircleShape
                    ),
            ) {
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    Icons.Default.Star,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimary
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "$animeRating",
                    style = TextStyle(
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
        }
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Text(
                text = "#$animeRank",
                style = MaterialTheme.typography.titleMedium,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = animeName,
                style = MaterialTheme.typography.titleLarge,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Bold,
                maxLines = 2,
                modifier = Modifier.fillMaxWidth()
                )
            Text(
                text = "Aired: $animeAiredDate",
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodyMedium,
            )
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            Text(
                text = animeType,
                style = MaterialTheme.typography.bodySmall,
                color = when (animeType) {
                    "TV" -> Color.Blue
                    "Movie" -> Color.Red
                    else -> Color.Green
                },
                modifier = Modifier.align(Alignment.BottomStart)
                )
        }
    }
}


@Preview
@Composable
fun AnimeCardItemPreview() {
    AnimeCardItem(
        1,
        "Bleach - Kessen Sennen-hen",
        9.11,
        "Oct 11, 2022 to Dec 27, 2022",
        "TV",
        "https://cdn.myanimelist.net/images/anime/1764/126627.jpg",
        {}
    )
}