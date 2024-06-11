package com.example.asmkot_ph44046.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
 fun CheckOutScreen(modifier: Modifier = Modifier, navController: NavController) {
    val scrollState = rememberScrollState()
    Column(
        modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(scrollState)
    ) {
        Row(
            modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    Icons.Default.KeyboardArrowLeft,
                    contentDescription = "",
                )
            }
            Text(
                text = "Check out",
                modifier = Modifier.fillMaxWidth(0.7f),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight(700),
                fontSize = 16.sp,
                fontFamily = GoogleFont.MerriweatherFont
            )
            Spacer(modifier.fillMaxWidth(0.35f))
        }
        EditTitleComponent(name = "Shipping address")
        Card(
            modifier
                .fillMaxWidth()
                .padding(10.dp), colors = CardDefaults.cardColors(Color.White),
            elevation = CardDefaults.cardElevation(2.dp)
        ) {
            Column {
                Spacer(modifier.height(15.dp))
                Text(
                    text = "Bruno Fernandes",
                    fontSize = 18.sp,
                    fontWeight = FontWeight(600),
                    fontFamily = GoogleFont.NunitoSansFont,
                    color = Color(
                        0xFF303030
                    ),
                    modifier = Modifier.padding(10.dp)
                )
                Divider()
                Text(
                    text = "25 rue Robert Latouche, Nice, 06200, Côte D’azur, France",
                    fontSize = 14.sp,
                    fontWeight = FontWeight(400),
                    fontFamily = GoogleFont.NunitoSansFont,
                    color = Color(
                        0xFF808080
                    ),
                    modifier = Modifier.padding(10.dp)
                )
                Spacer(modifier.height(15.dp))
            }
        }
        EditTitleComponent(name = "Payment")
        Card(
            modifier
                .fillMaxWidth()
                .padding(10.dp), colors = CardDefaults.cardColors(Color.White),
            elevation = CardDefaults.cardElevation(2.dp)
        ) {
            Spacer(modifier.height(10.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Card(
                    elevation = CardDefaults.cardElevation(5.dp),
                    modifier = Modifier
                        .height(55.dp)
                        .width(80.dp)
                        .padding(10.dp),
                    colors = CardDefaults.cardColors(Color.White),
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Image(
                            painterResource(id = R.drawable.cardpayment), contentDescription = null,
                            modifier
                                .width(32.dp)
                                .height(25.dp)
                        )
                    }
                }
                Text(
                    text = "**** **** **** 3947",
                    fontSize = 14.sp,
                    fontWeight = FontWeight(600),
                    fontFamily = GoogleFont.NunitoSansFont,
                    color = Color(
                        0xFF242424
                    ),
                )
            }
            Spacer(modifier.height(10.dp))
        }
        EditTitleComponent(name = "Delivery method")
        Card(
            modifier
                .fillMaxWidth()
                .padding(10.dp),
            colors = CardDefaults.cardColors(Color.White),
            elevation = CardDefaults.cardElevation(2.dp)
        ) {
            Spacer(modifier.height(10.dp))
            Row {
                Spacer(modifier.width(10.dp))
                Image(
                    painterResource(id = R.drawable.dhl), contentDescription = "",
                    modifier
                        .height(20.dp)
                        .width(90.dp)
                )
                Spacer(modifier.width(10.dp))
                Text(
                    text = "Fast (2-3days)",
                    fontSize = 14.sp,
                    fontWeight = FontWeight(700),
                    fontFamily = GoogleFont.NunitoSansFont,
                    color = Color(
                        0xFF303030
                    ),
                )
            }
            Spacer(modifier.height(10.dp))
        }
        Card(
            modifier
                .fillMaxWidth()
                .padding(10.dp),
            colors = CardDefaults.cardColors(Color.White),
            elevation = CardDefaults.cardElevation(2.dp)
        ) {
            Row(
                modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Order: ",
                    fontSize = 18.sp,
                    fontWeight = FontWeight(400),
                    fontFamily = GoogleFont.NunitoSansFont,
                    color = Color(0xFF909090),
                )
                Text(
                    text = "$95.00",
                    fontSize = 18.sp,
                    fontWeight = FontWeight(600),
                    fontFamily = GoogleFont.NunitoSansFont,
                    color = Color(0xFF242424)
                )
            }
            Row(
                modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Delivery: ",
                    fontSize = 18.sp,
                    fontWeight = FontWeight(400),
                    fontFamily = GoogleFont.NunitoSansFont,
                    color = Color(0xFF909090),
                )
                Text(
                    text = "$15.00",
                    fontSize = 18.sp,
                    fontWeight = FontWeight(600),
                    fontFamily = GoogleFont.NunitoSansFont,
                    color = Color(0xFF242424)
                )
            }
            Row(
                modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Total: ",
                    fontSize = 18.sp,
                    fontWeight = FontWeight(400),
                    fontFamily = GoogleFont.NunitoSansFont,
                    color = Color(0xFF909090),
                )
                Text(
                    text = "$115.00",
                    fontSize = 18.sp,
                    fontWeight = FontWeight(700),
                    fontFamily = GoogleFont.NunitoSansFont,
                    color = Color(0xFF242424)
                )
            }
        }
        Card(
            modifier
                .fillMaxWidth()
                .height(80.dp)
                .padding(10.dp),
            colors = CardDefaults.cardColors(Color(0xFF242424)),
            elevation = CardDefaults.cardElevation(2.dp),
            shape = RoundedCornerShape(10.dp)
        ) {
            Button(
                onClick = { navController.navigate("notification") }, modifier.fillMaxSize(),
                colors = ButtonDefaults.buttonColors(Color(0xFF242424))
            ) {
                Text(
                    text = "SUBMIT ORDER",
                    fontSize = 20.sp,
                    fontWeight = FontWeight(600),
                    fontFamily = GoogleFont.NunitoSansFont,
                    color = Color.White
                )
            }
        }
    }
}

@Composable
private fun EditTitleComponent(name: String) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Text(
            text = name,
            fontSize = 18.sp,
            fontWeight = FontWeight(600),
            fontFamily = GoogleFont.NunitoSansFont,
            color = Color(0xFF909090)
        )
        IconButton(onClick = { /*TODO*/ }) {
            Icon(Icons.Outlined.Edit, contentDescription = null)
        }
    }
}