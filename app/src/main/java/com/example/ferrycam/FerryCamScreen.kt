package com.example.ferrycam

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

enum class FerryCamScreen {
    // Start screen has been disabled
    // Change backstack and start screen to enable
    Start,
    Stream
}

@Composable
fun FerryCamAppBar(
    canNavigateBack: Boolean,
    navigateUp: () -> Unit
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
                        imageVector = Icons.Filled.Settings,
                        contentDescription = stringResource(R.string.settings_button).uppercase()
                    )
                }
            }
        }
    )
}

@Composable
fun FerryCamApp() {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    FerryCamScreen.valueOf(
        // Change to Start to enable Start screen
        backStackEntry?.destination?.route ?: FerryCamScreen.Start.name
    )
    NavHost(
        navController = navController,
        // Change to Start to enable Start screen
        startDestination = FerryCamScreen.Start.name,
        modifier = Modifier
    ) {
        composable(route = FerryCamScreen.Start.name) {
            StartScreen(onStartButtonClicked = {
                navController.navigate(FerryCamScreen.Stream.name)
            })
        }
        composable(route = FerryCamScreen.Stream.name) {
            StreamScreen()
        }
    }
    Box( modifier = Modifier.fillMaxSize() ) {
        FerryCamAppBar(
            canNavigateBack = (navController.previousBackStackEntry != null)
        ) { navController.navigateUp() }
    }

    Scaffold(
        topBar = { FerryCamAppBar(
            canNavigateBack = (navController.previousBackStackEntry != null)
        ) { navController.navigateUp() }
        },
    ) {
        NavHost(
            navController = navController,
            // Change to Start to enable Start screen
            startDestination = FerryCamScreen.Start.name,
        ) {
            composable(route = FerryCamScreen.Start.name) {
                StartScreen(onStartButtonClicked = {
                    navController.navigate(FerryCamScreen.Stream.name)
                })
            }
            composable(route = FerryCamScreen.Stream.name) {
                StreamScreen()
            }
        }
    }
}


