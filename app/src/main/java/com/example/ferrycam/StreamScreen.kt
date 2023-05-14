package com.example.ferrycam

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalConfiguration
import com.google.android.exoplayer2.C
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.StyledPlayerView

@Composable
fun StreamScreen() {
    var viewDefault by remember { mutableStateOf(true) }
    /*
    Box(
        modifier = Modifier
            .fillMaxSize()
    )
    {
        when (selectedButton) {
            1 -> StreamView(stringResource(R.string.settlement_queue_url))
            else -> StreamView(stringResource(R.string.settlement_ferry_url))
        }
    }*/

    val configuration = LocalConfiguration.current
    if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier.weight(1f)
            ) {
                StreamView(stringResource(R.string.settlement_ferry_url))
            }
            Box(
                modifier = Modifier.weight(1f)
            ) {
                StreamView(stringResource(R.string.settlement_queue_url))
            }
        }
    } else {
        Button(
            contentPadding = PaddingValues(0.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.background),
            onClick = { viewDefault = !viewDefault }) {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                when (viewDefault) {
                    false -> StreamView(stringResource(R.string.settlement_queue_url))
                    else -> StreamView(stringResource(R.string.settlement_ferry_url))
                }
                Box(
                    modifier = Modifier
                        .width(300.dp)
                        .height(100.dp)
                        .align(Alignment.BottomEnd)
                        .border(BorderStroke(1.dp, MaterialTheme.colors.secondary))
                ) {
                    when (viewDefault) {
                        true -> StreamView(stringResource(R.string.settlement_queue_url))
                        else -> StreamView(stringResource(R.string.settlement_ferry_url))
                    }
                }
            }
        }
    }

    /*
    Row(
    verticalAlignment = Alignment.Bottom,
    horizontalArrangement = Arrangement.Center,
    modifier = Modifier
        .fillMaxSize()
        .padding(start = 20.dp, bottom = 10.dp)
    ) {
        Button(onClick = { selectedButton = 0 }) {
            Text(stringResource(R.string.settlement_ferry_button).uppercase())
        }
        Spacer(
            modifier = Modifier.width(10.dp)
        )
        Button(onClick = { selectedButton = 1 }) {
            Text(stringResource(R.string.settlement_queue_button).uppercase())
        }
    }*/
}

@Composable
private fun BoxScope.StreamView(videoUri: String) {
    val context = LocalContext.current

    val exoPlayer = ExoPlayer.Builder(LocalContext.current)
        .build()
        .also { exoPlayer ->
            val mediaItem = MediaItem.Builder()
                .setUri(videoUri)
                .build()
            exoPlayer.setMediaItem(mediaItem)
            exoPlayer.prepare()
            exoPlayer.playWhenReady = true
            exoPlayer.videoScalingMode = C.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING
        }

    DisposableEffect(
        AndroidView(factory = {
            StyledPlayerView(context).apply {
                hideController()
                useController = false
                player = exoPlayer

            }
        }, modifier = Modifier
            .align(Alignment.Center)
            .fillMaxSize()
        )
    ) {
        onDispose {
            exoPlayer.release()
        }
    }
}


