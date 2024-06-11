package com.example.asmkot_ph44046.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.asmkot_ph44046.R


import com.example.asmkot_ph44046.font.GoogleFont

@Composable
fun NotificationScreen(modifier: Modifier = Modifier, navController: NavController) {
    Surface(
        modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(modifier.fillMaxSize()) {
            Row(
                modifier.fillMaxHeight(0.25f),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "SUCCESS!",
                    fontSize = 36.sp,
                    fontWeight = FontWeight(700),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontFamily = GoogleFont.MerriweatherFont
                )
            }
            Box(
                modifier.fillMaxWidth()
            ) {
                Image(
                    painterResource(id = R.drawable.notifi),
                    contentDescription = "123",
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .height(250.dp)
                        .align(Alignment.TopCenter)
                )
                Image(
                    painterResource(id = R.drawable.tick_icon),
                    contentDescription = "11111",
                    modifier
                        .size(50.dp)
                        .align(Alignment.BottomCenter),
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.3f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Your order will be delivered soon. Thank you for choosing our app!",
                    fontSize = 18.sp,
                    fontWeight = FontWeight(400),
                    modifier = Modifier.fillMaxWidth(0.8f),
                    textAlign = TextAlign.Center,
                    fontFamily = GoogleFont.NunitoSansFont,
                    color = Color(0xFF606060)
                )
            }
            Column(
                modifier
                    .fillMaxWidth()
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = { /*TODO*/ },
                    modifier.height(60.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(Color(0xFF242424))
                ) {
                    Text(
                        text = "Track your orders",
                        fontSize = 18.sp,
                        fontWeight = FontWeight(600),
                        modifier = Modifier.fillMaxWidth(0.8f),
                        textAlign = TextAlign.Center,
                        fontFamily = GoogleFont.NunitoSansFont,
                        color = Color.White
                    )
                }
                Spacer(modifier.height(20.dp))
                OutlinedButton(
                    onClick = { /*TODO*/ },
                    modifier.height(60.dp),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = "BACK TO HOME",
                        fontSize = 18.sp,
                        fontWeight = FontWeight(600),
                        modifier = Modifier.fillMaxWidth(0.8f),
                        textAlign = TextAlign.Center,
                        fontFamily = GoogleFont.NunitoSansFont,
                        color = Color.Black
                    )
                }
            }
        }
    }
}