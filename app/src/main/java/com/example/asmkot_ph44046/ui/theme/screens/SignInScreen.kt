package com.example.asmkot_ph44046.ui.screens

import android.content.Context
import android.widget.Toast
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
import com.example.asmkot_ph44046.database.entities.SaveUsers
import com.example.asmkot_ph44046.database.factory.SaveUserFactory
import com.example.asmkot_ph44046.database.factory.UserFactory
import com.example.asmkot_ph44046.database.repository.SaveUserRepository
import com.example.asmkot_ph44046.database.repository.UserRepository
import com.example.asmkot_ph44046.database.viewModel.SaveUserViewModel
import com.example.asmkot_ph44046.database.viewModel.UserViewModel

import com.example.asmkot_ph44046.font.GoogleFont

@Composable
fun SignInScreen(navController: NavController) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    //save user when sign up
    val userRepository =
        UserRepository(MyDatabase.getDatabase(context = context, scope = scope).userDao())
    val userViewModel: UserViewModel = viewModel(factory = UserFactory(userRepository))

    //save information
    val saveUserRepository =
        SaveUserRepository(MyDatabase.getDatabase(context, scope).saveUserDao())
    val saveUserViewModel: SaveUserViewModel =
        viewModel(factory = SaveUserFactory(saveUserRepository))

    //call component
    SignInScreenComponent(
        navController = navController,
        userViewModel = userViewModel,
        context = context,
        saveUserViewModel = saveUserViewModel
    )
}

@Composable
private fun SignInScreenComponent(
    modifier: Modifier = Modifier,
    navController: NavController,
    userViewModel: UserViewModel,
    saveUserViewModel: SaveUserViewModel,
    context: Context
) {
    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }

    val saveUser by saveUserViewModel.getUser.observeAsState(
        null
    )

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
                    1.dp,
                    Color.Black, RoundedCornerShape(100.dp)
                )
            ) {
                Icon(
                    painterResource(id = R.drawable.sofa), contentDescription = "",
                    modifier
                        .size(40.dp)
                        .padding(5.dp)
                )
            }
            Divider(
                thickness = 1.dp,
                modifier = Modifier
                    .padding(20.dp), color = Color(0xFFBDBDBD)
            )
        }
        Text(
            text = "Hello !", modifier.padding(10.dp),
            fontSize = 30.sp,
            color = Color(0xFF909090),
            fontFamily = GoogleFont.MerriweatherFont,
            fontWeight = FontWeight(400)
        )
        Text(
            text = "WELCOME BACK",
            fontSize = 24.sp,
            modifier = Modifier.padding(10.dp, 0.dp),
            fontWeight = FontWeight(700),
            color = Color(0xFF303030),
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
            Column(modifier.fillMaxHeight(0.4f), verticalArrangement = Arrangement.Center) {
                TextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text(text = "Email") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp, 0.dp, 0.dp, 0.dp),
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color.White,
                        focusedContainerColor = Color.White
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
                        unfocusedContainerColor = Color.White,
                        focusedContainerColor = Color.White
                    )
                )
            }
            Text(
                text = "Forgot Password",
                modifier
                    .fillMaxWidth()
                    .padding(0.dp, 10.dp, 0.dp, 0.dp),
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
                fontWeight = FontWeight(600),
                fontFamily = GoogleFont.NunitoSansFont
            )
            Column(modifier.fillMaxHeight(0.8f), verticalArrangement = Arrangement.Center) {
                Button(
                    onClick = {
                        if (email.isEmpty() && password.isEmpty()) {
                            Toast.makeText(context, "Data empty!", Toast.LENGTH_SHORT).show()
                        } else {
                            val result = userViewModel.userSignIn(email, password)
                            result.observeForever { listItem ->
                                if (listItem.size == 1) {
                                    Toast.makeText(context, "Đăng nhập thành công!", Toast.LENGTH_SHORT)
                                        .show()

                                    // Save email of user
                                    if (saveUser == null) saveUserViewModel.addUser(
                                        SaveUsers(
                                            1,
                                            email
                                        )
                                    )
                                    navController.navigate("home")
                                } else {
                                    Toast.makeText(
                                        context,
                                        "Thông tin không chính xác!",
                                        Toast.LENGTH_SHORT
                                    )
                                        .show()
                                }
                            }
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
                        text = "Log in",
                        fontSize = 18.sp,
                        fontWeight = FontWeight(600),
                        fontFamily = GoogleFont.NunitoSansFont
                    )
                }
                Spacer(modifier.height(20.dp))
                ClickableText(
                    text = AnnotatedString("SIGN UP"),
                    onClick = {
                        navController.navigate("signup")
                    },
                    modifier = Modifier.fillMaxWidth(),
                    style = TextStyle(
                        textAlign = TextAlign.Center,
                        fontSize = 18.sp,
                        fontWeight = FontWeight(400),
                        fontFamily = GoogleFont.NunitoSansFont
                    )
                )
            }
        }
    }
}