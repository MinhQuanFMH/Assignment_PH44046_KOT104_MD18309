package com.example.asmkot_ph44046.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.asmkot_ph44046.font.GoogleFont

@Composable
fun HeaderToolBar(navController: NavController, title: String, modifier: Modifier = Modifier) {
    Row(
        modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { navController.popBackStack() }) {
            Icon(
                Icons.Default.KeyboardArrowLeft,
                contentDescription = "",
            )
        }
        Text(
            text = "My Cart",
            modifier = Modifier.fillMaxWidth(0.7f),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight(700),
            fontSize = 16.sp,
            fontFamily = GoogleFont.MerriweatherFont
        )
        Spacer(modifier.fillMaxWidth(0.35f))
    }
}