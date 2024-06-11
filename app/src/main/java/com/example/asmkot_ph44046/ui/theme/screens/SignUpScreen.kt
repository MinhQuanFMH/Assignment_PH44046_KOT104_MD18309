package com.example.asmkot_ph44046.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.asmkot_ph44046.R
import com.example.asmkot_ph44046.database.MyDatabase
import com.example.asmkot_ph44046.database.entities.Users
import com.example.asmkot_ph44046.database.factory.UserFactory
import com.example.asmkot_ph44046.database.repository.UserRepository
import com.example.asmkot_ph44046.database.viewModel.UserViewModel
import com.example.asmkot_ph44046.font.GoogleFont
import com.example.asmkot_ph44046.utils.AppUtils


@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val userRepository =
        UserRepository(MyDatabase.getDatabase(context = context, scope = scope).userDao())
    val viewModel: UserViewModel = viewModel(factory = UserFactory(userRepository))
    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    var name by remember {
        mutableStateOf("")
    }
    var confirmPassword by remember {
        mutableStateOf("")
    }
    val userExist by viewModel.getUser(email).observeAsState(null)
    Column(
        modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Row(
            modifier
                .fillMaxWidth()
                .fillMaxHeight(0.15f),
            verticalAlignment = Alignment.Bottom,
        ) {
            Divider(
                thickness = 1.dp,
                modifier = Modifier
                    .fillMaxWidth(0.45f)
                    .padding(20.dp),
                color = Color(0xFFBDBDBD)
            )
            Row(
                modifier.border(
                    1.dp, Color.Black, RoundedCornerShape(100.dp)
                )
            ) {
                Icon(
                    painterResource(id = R.drawable.sofa),
                    contentDescription = "",
                    modifier
                        .size(40.dp)
                        .padding(5.dp)
                )
            }
            Divider(
                thickness = 1.dp, modifier = Modifier.padding(20.dp), color = Color(0xFFBDBDBD)
            )
        }
        Text(
            text = "WELCOME BACK",
            fontSize = 24.sp,
            modifier = Modifier.padding(10.dp, 20.dp, 0.dp, 10.dp),
            fontWeight = FontWeight(700),
            fontFamily = GoogleFont.MerriweatherFont
        )
        Spacer(modifier.height(20.dp))
        Card(
            modifier
                .fillMaxHeight(0.8f)
                .fillMaxWidth()
                .padding(0.dp, 0.dp, 20.dp, 0.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
            colors = CardDefaults.cardColors(Color.White)
        ) {
            Column(modifier.fillMaxHeight(0.7f), verticalArrangement = Arrangement.Center) {
                TextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text(text = "Name") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp, 0.dp, 0.dp, 0.dp),
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color.White, focusedContainerColor = Color.White
                    )
                )
                Spacer(modifier.height(20.dp))
                TextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text(text = "Email") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp, 0.dp, 0.dp, 0.dp),
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color.White, focusedContainerColor = Color.White
                    )
                )
                Spacer(modifier.height(20.dp))
                TextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text(text = "Password") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp, 0.dp, 0.dp, 0.dp),
                    visualTransformation = PasswordVisualTransformation(),
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color.White, focusedContainerColor = Color.White
                    )
                )
                Spacer(modifier.height(20.dp))
                TextField(
                    value = confirmPassword,
                    onValueChange = { confirmPassword = it },
                    label = { Text(text = "Confirm Password") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp, 0.dp, 0.dp, 0.dp),
                    visualTransformation = PasswordVisualTransformation(),
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color.White, focusedContainerColor = Color.White
                    )
                )
            }

            Column(modifier.fillMaxHeight(1f), verticalArrangement = Arrangement.Center) {
                Button(
                    onClick = {
                        when {
                            email.isEmpty() && name.isEmpty() && password.isEmpty() && confirmPassword.isEmpty() -> AppUtils.ToastUtils(
                                context, "Data empty!"
                            )

                            password != confirmPassword -> AppUtils.ToastUtils(
                                context, "Password not match!"
                            )

                            userExist == null -> {
                                val result = viewModel.userSignUp(Users(0, email, name, password))
                                AppUtils.ToastUtils(context, "$result")
                            }

                            else -> AppUtils.ToastUtils(context, "Account exist!")
                        }

                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp, 0.dp)
                        .height(50.dp)
                ) {
                    Text(
                        text = "Sign Up",
                        fontSize = 18.sp,
                        fontFamily = GoogleFont.NunitoSansFont,
                        fontWeight = FontWeight(600)
                    )
                }
                Spacer(modifier.height(20.dp))
                Row(
                    modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Already have account?",
                        fontSize = 14.sp,
                        fontWeight = FontWeight(600)
                    )
                    ClickableText(
                        text = AnnotatedString("SIGN IN"), onClick = {
                            navController.navigate("signin")
                        }, style = TextStyle(
                            textAlign = TextAlign.Center,
                            fontSize = 14.sp,
                            fontWeight = FontWeight(700)
                        )
                    )
                }
            }
        }
    }
}