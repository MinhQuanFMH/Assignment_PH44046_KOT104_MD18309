package com.example.asmkot_ph44046.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.asmkot_ph44046.R

import com.example.asmkot_ph44046.font.GoogleFont

@Composable
fun WelcomeScreen(modifier: Modifier = Modifier, navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painterResource(id = R.drawable.welcome_img),
            contentDescription = "",
            modifier.fillMaxSize()
        )
        Column(
            modifier
                .fillMaxHeight()
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "MAKE YOUR",
                modifier.padding(10.dp),
                fontSize = 24.sp,
                fontFamily = GoogleFont.GelasioFont,
                fontWeight = FontWeight(600),
                color = Color(0xFF606060)
            )
            Text(
                text = "HOME BEAUTIFUL",
                modifier.padding(10.dp, 0.dp),
                fontSize = 30.sp,
                fontWeight = FontWeight(700),
                fontFamily = GoogleFont.GelasioFont,
                color = Color(0xFF303030)
            )
            Spacer(modifier.height(50.dp))
            Text(
                text = "The best simple place where you",
                modifier.padding(30.dp, 0.dp),
                fontSize = 18.sp,
                fontFamily = GoogleFont.NunitoSansFont
            )
            Text(
                text = "discover most wonderful furnitures",
                modifier.padding(30.dp, 10.dp),
                fontSize = 18.sp,
                fontFamily = GoogleFont.NunitoSansFont
            )
            Text(
                text = "and make you your home beautiful",
                modifier.padding(30.dp, 0.dp),
                fontSize = 18.sp,
                fontFamily = GoogleFont.NunitoSansFont
            )
            Row(
                modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.4f),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.Bottom
            ) {
                Button(
                    onClick = {
                        navController.navigate("signin")
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(
                        text = "Get Started",
                        modifier.padding(10.dp, 5.dp),
                        fontSize = 18.sp,
                        fontFamily = GoogleFont.GelasioFont,
                    )
                }
            }
        }
    }
}
