package com.example.ferrycam

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.ferrycam.ui.theme.FerryCamTheme
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.unit.dp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FerryCamTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    FerryCamApp()
                }
            }
        }
    }
}



// For displaying preview in
// the Android Studio IDE emulator
@Preview(
    name = "Preview1",
    device = Devices.PIXEL,
    showSystemUi = true

)
@Composable
fun StreamPreview() {
    FerryCamApp()
    Box( modifier = Modifier.fillMaxSize() ) {
        Box(
            modifier = Modifier
                .background(color = Color.Black)
                .fillMaxWidth()
                .height(200.dp)
                .align(Alignment.Center)
        )
    }

}
