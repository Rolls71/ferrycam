package com.example.ferrycam

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import com.google.android.exoplayer2.ui.StyledPlayerView
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

enum class FerryCamScreen(@StringRes val title: Int) {
    // Start screen has been disabled
    // Change backstack and start screen to enable
    Start(title = R.string.app_name),
    Stream(title = R.string.stream_title)
}

@Composable
fun FerryCamAppBar(
    currentScreen: FerryCamScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(
            stringResource(R.string.stream_title),
            color = MaterialTheme.colors.onPrimary
        ) },
        elevation = 0.dp,
        backgroundColor = MaterialTheme.colors.primary,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button).uppercase()
                    )
                }
            }
        }
    )
}

@Composable
fun FerryCamApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = FerryCamScreen.valueOf(
        // Change to Start to enable Start screen
        backStackEntry?.destination?.route ?: FerryCamScreen.Stream.name
    )/*
    NavHost(
        navController = navController,
        // Change to Start to enable Start screen
        startDestination = FerryCamScreen.Stream.name,
        modifier = Modifier
    ) {
        composable(route = FerryCamScreen.Start.name) {
            StartScreen(onStartOrderButtonClicked = {
                navController.navigate(FerryCamScreen.Stream.name)
            })
        }
        composable(route = FerryCamScreen.Stream.name) {
            StreamScreen()
        }
    }
    Box( modifier = Modifier.fillMaxSize() ) {
        FerryCamAppBar(
            currentScreen = currentScreen,
            canNavigateBack = (navController.previousBackStackEntry != null),
            navigateUp = { navController.navigateUp() },
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .align(Alignment.TopCenter)
        )
    }*/

    Scaffold(
        topBar = { FerryCamAppBar(
            currentScreen = currentScreen,
            canNavigateBack = (navController.previousBackStackEntry != null),
            navigateUp = { navController.navigateUp() },
            modifier = Modifier.height(0.dp)
        ) },
    ) {
        NavHost(
            navController = navController,
            // Change to Start to enable Start screen
            startDestination = FerryCamScreen.Stream.name,
        ) {
            composable(route = FerryCamScreen.Start.name) {
                StartScreen(onStartOrderButtonClicked = {
                    navController.navigate(FerryCamScreen.Stream.name)
                })
            }
            composable(route = FerryCamScreen.Stream.name) {
                StreamScreen()
            }
        }
    }
}


