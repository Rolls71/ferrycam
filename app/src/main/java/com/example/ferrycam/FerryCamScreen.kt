package com.example.ferrycam

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
    Settings,
    Stream
}

@Composable
fun FerryCamAppBar(
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    navigateSettings: () -> Unit,
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
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            } else {
                IconButton(onClick = navigateSettings) {
                    Icon(
                        imageVector = Icons.Filled.Settings,
                        contentDescription = stringResource(R.string.settings_button)
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
        backStackEntry?.destination?.route ?: FerryCamScreen.Stream.name
    )
    NavHost(
        navController = navController,
        startDestination = FerryCamScreen.Stream.name,
        modifier = Modifier
    ) {
        composable(route = FerryCamScreen.Settings.name) {
            SettingsScreen()
        }
        composable(route = FerryCamScreen.Stream.name) {
            StreamScreen()
        }
    }

    Scaffold(
        topBar = { FerryCamAppBar(
            canNavigateBack = (navController.previousBackStackEntry != null),
            navigateUp = { navController.navigateUp() },
            navigateSettings = { navController.navigate(FerryCamScreen.Settings.name)}
        )
        },
    ) {
        NavHost(
            navController = navController,
            startDestination = FerryCamScreen.Stream.name,
        ) {
            composable(route = FerryCamScreen.Settings.name) {
                SettingsScreen()
            }
            composable(route = FerryCamScreen.Stream.name) {
                StreamScreen()
            }
        }
    }
}


