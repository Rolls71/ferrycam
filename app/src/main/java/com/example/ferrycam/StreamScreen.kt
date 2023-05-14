package com.example.ferrycam

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.android.exoplayer2.C
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout
import com.google.android.exoplayer2.ui.StyledPlayerView

@Composable
fun StreamScreen() {
    var selectedButton by remember { mutableStateOf(0) }
    Box(
        modifier = Modifier
            .fillMaxSize()
    )
    {
        when (selectedButton) {
            1 -> StreamView(stringResource(R.string.settlement_queue_url))
            else -> StreamView(stringResource(R.string.settlement_ferry_url))
        }
    }
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
    }
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


