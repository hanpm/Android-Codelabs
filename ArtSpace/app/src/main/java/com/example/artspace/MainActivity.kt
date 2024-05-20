package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtSpaceLayout()
                }
            }
        }
    }
}

@Composable
fun ArtSpaceLayout(modifier: Modifier = Modifier) {
    val firstArt = R.drawable.michael
    val secondArt = R.drawable.flower_one
    val thirdArt = R.drawable.girl

    var currentArt by remember { mutableStateOf(firstArt) }
    var artDescription by remember {
        mutableStateOf(R.string.michael_description)
    }

    Column(
        modifier = Modifier
            .statusBarsPadding()
            .padding(horizontal = 50.dp)
            .safeDrawingPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
        ) {
        Surface(
            modifier = Modifier
                .height(400.dp)
                .width(500.dp),
            shadowElevation =  10.dp

        ) {
            Image(painter = painterResource(id = currentArt), contentDescription = "art piece", Modifier.padding(horizontal = 30.dp))
        }
        Surface(
            modifier = Modifier
                .height(125.dp)
                .width(500.dp)
                .padding(top = 40.dp),
            color = Color.LightGray,
            shadowElevation =  15.dp) {
            Text(
                text = stringResource(id = artDescription),
                modifier = Modifier
                    .padding(10.dp)
            )
        }
        Row(
            modifier = Modifier.padding(top = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(90.dp),
        ) {
            // Previous Button
            Button(onClick = {
                when (currentArt) {
                    firstArt -> {
                        currentArt = thirdArt
                        artDescription = R.string.girl_description
                    }
                    secondArt -> {
                        currentArt = firstArt
                        artDescription = R.string.michael_description
                    }
                    thirdArt -> {
                        currentArt = secondArt
                        artDescription = R.string.flower_one_description
                    }
                    else -> {
                        currentArt = thirdArt
                        artDescription = R.string.girl_description
                    }
                }
            }) {
                Text("Previous")
            }
            // Next
            Button(onClick = {
                when (currentArt) {
                    firstArt -> {
                        currentArt = secondArt
                        artDescription = R.string.flower_one_description
                    }
                    secondArt -> {
                        currentArt = thirdArt
                        artDescription = R.string.girl_description
                    }
                    thirdArt -> {
                        currentArt = firstArt
                        artDescription = R.string.michael_description
                    }
                    else -> {
                        currentArt = firstArt
                        artDescription = R.string.michael_description
                    }
                }
            }) {
                Text("Next")
            }
        }
    }
}

//Applying state hoisting pattern to make state shared with other composables

@Composable
fun ChangeArtPiece(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {


}

@Preview(showBackground = true)
@Composable
fun ArtSpaceLayoutPreview() {
    ArtSpaceTheme {
        ArtSpaceLayout()
    }
}