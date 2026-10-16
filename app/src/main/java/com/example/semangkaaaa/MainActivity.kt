package com.example.semangkaaaa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.napittugas1.R
import com.example.semangkaaaa.ui.theme.SemangkaaaaTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            SemangkaaaaTheme {
                SemangkaApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SemangkaApp() {

    var currentStep by remember { mutableIntStateOf(1) }

    var cutCount by remember { mutableIntStateOf(0) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Semangka",
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(),
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Red,
                    titleContentColor = Color.White,
                )
            )
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (currentStep) {
                1 -> {
                    SemangkaTextAndImage(
                        textLabel = stringResource(R.string.semangka_select),
                        drawableResourceId = R.drawable.semangka_utuh,
                        contentDescription = stringResource(R.string.semangka_tree_content_description),
                        onImageClick = {
                            currentStep = 2
                            cutCount = (2..4).random()
                        }
                    )
                }
                2 -> {
                    SemangkaTextAndImage(
                        textLabel = stringResource(R.string.semangka_cut),
                        drawableResourceId = R.drawable.semangka_potongan,
                        contentDescription = stringResource(R.string.semangka_content_description),
                        onImageClick = {
                            cutCount--
                            if (cutCount == 0) {
                                currentStep = 3
                            }
                        }
                    )
                }

                3 -> {
                    SemangkaTextAndImage(
                        textLabel = stringResource(R.string.semangka_drink),
                        drawableResourceId = R.drawable.semangka_jus,
                        contentDescription = stringResource(R.string.semangka_drink_content_description),
                        onImageClick = {
                            currentStep = 4
                        }
                    )
                }
                4 -> {
                    SemangkaTextAndImage(
                        textLabel = stringResource(R.string.semangka_empty_glass),
                        drawableResourceId = R.drawable.gelas_habis,
                        contentDescription = stringResource(R.string.empty_glass_content_description),
                        onImageClick = {
                            currentStep = 1
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun SemangkaTextAndImage(
    textLabel: String,
    drawableResourceId: Int,
    contentDescription: String,
    onImageClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(drawableResourceId),
                contentDescription = contentDescription,
                modifier = Modifier
                    .width(160.dp)
                    .height(160.dp)
                    .clickable(onClick = onImageClick)
            )
            Spacer(
                modifier = Modifier.height(32.dp)
            )
            Text(
                text = textLabel,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Preview
@Composable
fun SemangkaPreview() {
    SemangkaaaaTheme {
        SemangkaApp()
    }
}
