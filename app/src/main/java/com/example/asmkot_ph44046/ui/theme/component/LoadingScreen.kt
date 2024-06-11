package com.example.asmkot_ph44046.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.delay

@Composable
fun LoadingScreen() {
    var currentDotState by remember { mutableStateOf(0) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(1000L)
            currentDotState = (currentDotState + 1) % 4
        }
    }

    Row(
        modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        when (currentDotState) {
            0 -> Text(text = "Loading", style = MaterialTheme.typography.titleLarge)
            1 -> Text(text = "Loading.", style = MaterialTheme.typography.titleLarge)
            2 -> Text(text = "Loading..", style = MaterialTheme.typography.titleLarge)
            3 -> Text(text = "Loading...", style = MaterialTheme.typography.titleLarge)
        }
    }
}