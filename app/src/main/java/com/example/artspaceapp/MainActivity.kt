package com.example.artspaceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.graphics.Color
import com.example.artspaceapp.ui.theme.ArtSpaceAppTheme



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    ArtSpaceScreen()
                }
            }
        }
    }
}

@Composable
fun ArtSpaceScreen() {
    val artworks = listOf(
        Artwork(R.drawable.the_starry_night, "The Starry Night", "Vincent van Gogh", 1889),
        Artwork(R.drawable.mona_lisa_leonardo_da_vinci, "Mona Lisa", "Leonardo da Vinci", 1503),
        Artwork(R.drawable.the_scream, "The Scream", "Edvard Munch", 1893)
    )

    var currentIndex by remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Move everything slightly down for better positioning
        Spacer(modifier = Modifier.height(100.dp))

        // Artwork Image Box
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp),
            contentAlignment = Alignment.Center
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .height(400.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                shape = RoundedCornerShape(0.dp) // Sharp corners
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = artworks[currentIndex].imageRes),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth(0.85f)
                            .height(350.dp)
                            .aspectRatio(4f / 5f)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))


        Spacer(modifier = Modifier.height(100.dp))

        // Artwork Details Card
        Card(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .weight(1f),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            shape = RoundedCornerShape(0.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = artworks[currentIndex].title,
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Light
                )
                Text(
                    text = "${artworks[currentIndex].artist} (${getYearText(artworks[currentIndex].year)})",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        // Navigation Buttons
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = {
                    currentIndex = if (currentIndex > 0) currentIndex - 1 else artworks.size - 1
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Previous", fontSize = 22.sp)
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(
                onClick = {
                    currentIndex = if (currentIndex < artworks.size - 1) currentIndex + 1 else 0
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Next", fontSize = 22.sp)
            }
        }
    }
}

// Function to style the year text
@Composable
fun getYearText(year: Int): AnnotatedString {
    return buildAnnotatedString {
        withStyle(style = SpanStyle(fontWeight = FontWeight.Light)) {
            append("$year")
        }
    }
}

// Data class for Artwork
data class Artwork(val imageRes: Int, val title: String, val artist: String, val year: Int)

@Preview(showBackground = true)
@Composable
fun ArtSpacePreview() {
    ArtSpaceAppTheme {
        ArtSpaceScreen()
    }
}
