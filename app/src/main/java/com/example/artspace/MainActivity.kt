package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    ArtSpaceApp()
                }
            }
        }
    }
}

@Composable
fun ArtSpaceApp() {
    val artworks = listOf(
        Artwork(R.drawable.art1, "UIN SUSKA RIAU", "SUSKA RIAU", 2024, "Universitas Islam Negeri Sultan Syarif Kasim Riau", "Fotografi", 4.8f, true),
        Artwork(R.drawable.art2, "KUOK", "Persiapan Asomatif", 2023, "Persiapan atlet dalam turnamen tahunan", "Olahraga", 4.5f, false),
        Artwork(R.drawable.art3, "Kucing Oren", "Rawrrrrr", 2025, "Ilustrasi kucing oren menggemaskan", "Ilustrasi", 5.0f, true),
        Artwork(R.drawable.art4, "Atlet Basket", "2020-2025", 2021, "Foto atlet basket sedang bertanding", "Olahraga", 4.2f, false),
        Artwork(R.drawable.art5, "Porseni PUBG", "Tahun 2025", 2025, "Pertandingan eSports PUBG di ajang Porseni", "eSports", 4.9f, true)
    )

    var currentArtworkIndex by remember { mutableStateOf(0) }
    val currentArtwork = artworks[currentArtworkIndex]

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = currentArtwork.imageRes),
            contentDescription = currentArtwork.title,
            modifier = Modifier
                .size(300.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(MaterialTheme.colorScheme.surfaceVariant)
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "${currentArtwork.title} (${currentArtwork.year})",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "By: ${currentArtwork.artist}",
            fontSize = 16.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Kategori: ${currentArtwork.category}",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Blue
        )

        Text(
            text = currentArtwork.description,
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(16.dp))
        Row {
            repeat(5) { index ->
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "Rating",
                    tint = if (index < currentArtwork.rating.toInt()) Color.Yellow else Color.Gray,
                    modifier = Modifier.size(18.dp)
                )
            }
            Text(
                text = " ${currentArtwork.rating}",
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { /* Tambahkan aksi favorit */ },
            colors = ButtonDefaults.buttonColors(
                containerColor = if (currentArtwork.isFavorite) Color.Red else Color.Gray
            )
        ) {
            Text(if (currentArtwork.isFavorite) "Favorit ❤️" else "Tambah ke Favorit")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween) {
            Button(
                onClick = { if (currentArtworkIndex > 0) currentArtworkIndex-- },
                enabled = currentArtworkIndex > 0,
                modifier = Modifier.padding(8.dp)
            ) {
                Text("Sebelumnya")
            }

            Button(
                onClick = { if (currentArtworkIndex < artworks.size - 1) currentArtworkIndex++ },
                enabled = currentArtworkIndex < artworks.size - 1
            ) {
                Text("Berikutnya")
            }
        }
    }
}

data class Artwork(
    val imageRes: Int,
    val title: String,
    val artist: String,
    val year: Int,
    val description: String,
    val category: String,
    val rating: Float,
    val isFavorite: Boolean
)

@Preview(showBackground = true)
@Composable
fun ArtSpacePreview() {
    ArtSpaceTheme {
        ArtSpaceApp()
    }
}
