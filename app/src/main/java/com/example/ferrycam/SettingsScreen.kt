package com.example.ferrycam

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier
) {
    val notificationsEnabled = remember{ mutableStateOf(true) }
    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        notificationsEnabled.value =
            settingsSwitch(stringResource(R.string.allow_notifications), true)

        Divider(
            thickness = 1.dp,
            color = MaterialTheme.colors.primary,
            modifier = Modifier.padding(bottom = 10.dp)
        )
        Column(
            modifier = Modifier.weight(2f)
                .padding(start = 10.dp)
        ) {
            settingsSwitch(
                stringResource(R.string.ferry_down_notifications),
                stringResource(R.string.ferry_down_description),
                notificationsEnabled.value
            )
            settingsSwitch(
                stringResource(R.string.ferry_traffic_notifications),
                stringResource(R.string.ferry_traffic_description),
                notificationsEnabled.value
            )
        }
        Divider(
            thickness = 1.dp,
            color = MaterialTheme.colors.primary,
            modifier = Modifier.padding(bottom = 10.dp)
        )
        Column(
            modifier = Modifier.weight(3f)
                .padding(start = 10.dp)
        ) {

            settingsSwitch(stringResource(R.string.floating_notifications), notificationsEnabled.value)
            settingsSwitch(stringResource(R.string.lock_screen_notifications), notificationsEnabled.value)
            settingsSwitch(stringResource(R.string.sound_notifications), notificationsEnabled.value)
            settingsSwitch(stringResource(R.string.vibration_notifications), notificationsEnabled.value)
        }
        Spacer(modifier = Modifier.weight(2f))
    }
}
@Composable
fun settingsSwitch(string: String, isEnabled: Boolean): Boolean {
    val stateToggle = remember{ mutableStateOf(true)}
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ){
        Text(
            text = string,
            fontSize = 20.sp,
            modifier = Modifier.align(Alignment.CenterStart)
        )
        Switch(
            checked = stateToggle.value,
            onCheckedChange = {stateToggle.value = it},
            enabled = isEnabled,
            modifier = Modifier.align(Alignment.CenterEnd)
        )
    }
    return stateToggle.value
}

@Composable
fun settingsSwitch(topString: String, bottomString: String, isEnabled: Boolean): Boolean {
    val stateToggle = remember{ mutableStateOf(true)}
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ){
        Column( modifier = Modifier
            .align(Alignment.TopStart)
            .width(250.dp)) {
            Text(
                text = topString,
                fontSize = 20.sp,
            )

            Text(
                text = bottomString,
                color = MaterialTheme.colors.surface,
            )
        }
        Switch(
            checked = stateToggle.value,
            onCheckedChange = {stateToggle.value = it},
            enabled = isEnabled,
            modifier = Modifier.align(Alignment.TopEnd)
        )
    }
    return stateToggle.value
}