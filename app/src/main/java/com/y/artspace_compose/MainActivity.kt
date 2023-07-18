package com.y.artspace_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.y.artspace_compose.ui.theme.ArtSpace_composeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpace_composeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ArtApp()
                }
            }
        }
    }
}

@Composable
fun ArtApp() {
    val currentIndex = remember { mutableStateOf(0) }

    var currentArtImg = R.drawable.chelong
    // 별 다른 내용을 넣지 않을 것이므로 title, description을 모두 같은 값으로 설정.
    var currentArtDescription = "크롱"
    when (currentIndex.value) {
        0 -> {
            currentArtImg = R.drawable.chelong
            currentArtDescription = "크롱"
        }
        1 -> {
            currentArtImg = R.drawable.janggu
            currentArtDescription = "짱구"
        }
        2 -> {
            currentArtImg = R.drawable.pororo
            currentArtDescription = "뽀로로"
        }
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ArtImage(currentImage = currentArtImg, description = currentArtDescription)
        // 별 다른 내용을 넣지 않을 것이므로 title, description을 모두 같은 값으로 설정함.
        ArtTitleandDescription(title = currentArtDescription, description = currentArtDescription)

        ArtButtons(
            onPreviousButtonClick = {
                currentIndex.value--
                if (currentIndex.value < 0) currentIndex.value = 2
            },
            onNextButtonClick = {
                currentIndex.value++
                if(currentIndex.value > 2) currentIndex.value = 0
            }
        )

    }
}

@Composable
fun ArtImage(currentImage: Int, description: String, modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = currentImage),
        contentDescription = description,
        modifier = modifier.fillMaxWidth(),
        contentScale = ContentScale.FillWidth
    )
}

@Composable
fun ArtTitleandDescription(
    title: String,
    description: String,
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = description,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp
        )
    }
}

@Composable
fun ArtButtons(onPreviousButtonClick: () -> Unit, onNextButtonClick: () -> Unit) {
    Row {
        Button(onClick = { onPreviousButtonClick() }) {
            Text(text = "Previous")
        }
        Button(onClick = { onNextButtonClick() }) {
            Text(text = "Next")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ArtAppPreview() {
    ArtSpace_composeTheme {
        ArtApp()
    }
}