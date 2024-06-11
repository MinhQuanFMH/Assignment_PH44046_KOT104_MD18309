package com.example.asmkot_ph44046.ui.screens

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.RemoveCircleOutline
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.asmkot_ph44046.database.MyDatabase
import com.example.asmkot_ph44046.database.entities.Carts
import com.example.asmkot_ph44046.database.factory.CartFactory
import com.example.asmkot_ph44046.database.factory.SaveUserFactory
import com.example.asmkot_ph44046.database.repository.CartRepository
import com.example.asmkot_ph44046.database.repository.SaveUserRepository
import com.example.asmkot_ph44046.database.viewModel.CartViewModel
import com.example.asmkot_ph44046.database.viewModel.SaveUserViewModel
import com.example.asmkot_ph44046.font.GoogleFont
import com.example.asmkot_ph44046.ui.component.DialogConfirm
import com.example.asmkot_ph44046.ui.component.HeaderToolBar
import com.example.asmkot_ph44046.ui.component.LoadingScreen


var cartItems: Carts? = null

@Composable
private fun ListItemCart(cartViewModel: CartViewModel, email: String, openDialog: () -> Unit = {}) {
    val itemsList by cartViewModel.getItemInCart(email).observeAsState(emptyList())
    LazyColumn {
        items(itemsList) { item ->
            ItemCard(item = item, openDialog = openDialog, cartViewModel = cartViewModel)
        }
    }
}

@Composable
private fun ItemCard(
    item: Carts,
    modifier: Modifier = Modifier,
    openDialog: () -> Unit,
    cartViewModel: CartViewModel
) {
    var quantity by remember {
        mutableIntStateOf(item.quantity)
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Card(modifier = Modifier.size(100.dp)) {
            AsyncImage(
                model = item.imgProduct,
                contentDescription = "${item.id}",
                contentScale = ContentScale.Crop
            )
        }
        Column(modifier = Modifier.fillMaxWidth(0.9f)) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = item.nameProduct,
                    fontFamily = GoogleFont.NunitoSansFont,
                    fontWeight = FontWeight(600),
                    fontSize = 14.sp,
                    color = Color(0xFF999999)
                )
                Surface(
                    shape = RoundedCornerShape(50.dp),
                    border = BorderStroke(1.dp, Color.Black),
                    modifier = Modifier
                        .size(20.dp)
                        .fillMaxWidth(),
                ) {
                    IconButton(
                        onClick = {
                            openDialog()
                            cartItems = item
                        },
                        modifier = Modifier.background(Color.White)
                    ) {
                        Icon(
                            Icons.Default.Clear,
                            contentDescription = "",
                            modifier = Modifier.size(15.dp)
                        )
                    }
                }
            }
            Text(
                text = "$ ${item.priceProduct}",
                fontFamily = GoogleFont.NunitoSansFont,
                fontWeight = FontWeight(700),
                fontSize = 16.sp,
                color = Color(0xFF242424)
            )
            Row(
                verticalAlignment = Alignment.Bottom,
                modifier = Modifier.fillMaxHeight()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Card(
                        modifier.size(30.dp),
                        shape = RoundedCornerShape(5.dp),
                        colors = CardDefaults.cardColors(
                            Color(0xFFE0E0E0)
                        )
                    ) {
                        IconButton(onClick = {
                            quantity++
                            val newCart = item
                            item.quantity = quantity
                            cartViewModel.updateInCart(newCart)
                        }) {
                            Icon(Icons.Default.Add, contentDescription = null)
                        }
                    }
                    Spacer(modifier.width(10.dp))
                    Text(
                        text = "$quantity",
                        fontFamily = GoogleFont.NunitoSansFont,
                        fontSize = 18.sp,
                        fontWeight = FontWeight(600),
                        color = Color(0xFF242424)
                    )
                    Spacer(modifier.width(10.dp))
                    Card(modifier.size(30.dp), shape = RoundedCornerShape(5.dp)) {
                        IconButton(onClick = {
                            if (quantity > 1) quantity--
                            val newCart = item
                            item.quantity = quantity
                            cartViewModel.updateInCart(newCart)
                        }) {
                            Icon(
                                Icons.Filled.RemoveCircleOutline,
                                contentDescription = null,
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CartScreen(modifier: Modifier = Modifier, navController: NavController) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val cartRepository = CartRepository(MyDatabase.getDatabase(context, scope).cartDao())
    val saveUserRepository =
        SaveUserRepository(MyDatabase.getDatabase(context, scope).saveUserDao())
    val cartViewModel: CartViewModel = viewModel(factory = CartFactory(cartRepository))
    val saveUserViewModel: SaveUserViewModel =
        viewModel(factory = SaveUserFactory(saveUserRepository))

    val saveUser by saveUserViewModel.getUser.observeAsState(null)

    var dialogShow by remember {
        mutableStateOf(false)
    }
    var priceTotal by remember {
        mutableIntStateOf(0)
    }
    if (saveUser != null) {
        cartViewModel.getTotalPrice(saveUser!!.email)
        val total by cartViewModel.totalPrice.observeAsState(0)
        priceTotal = total
    }
    when {
        dialogShow -> DialogConfirm(
            onDismissRequest = { dialogShow = false },
            onConfirmation = { cartViewModel.removeFromCart(cartItems!!);dialogShow = false },
            dialogTitle = "Remove product",
            dialogText = "Remove product from cart?"
        )
    }

    Box(
        modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column {
            HeaderToolBar(navController = navController, title = "My Cart")
            when {
                saveUser != null -> ListItemCart(
                    cartViewModel = cartViewModel,
                    email = saveUser!!.email,
                    openDialog = { dialogShow = true }
                )

                else -> LoadingScreen()
            }

        }
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(10.dp),
        ) {
            Row(
                modifier
                    .fillMaxWidth()
                    .height(45.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth(0.85f),
                    colors = CardDefaults.cardColors(
                        Color.White,
                    ), elevation = CardDefaults.elevatedCardElevation()
                ) {
                    TextField(
                        value = "",
                        onValueChange = {},
                        label = { Text(text = "Enter your promo code") },
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = Color.White,
                            focusedContainerColor = Color.White
                        )
                    )
                }
                Card(
                    shape = RoundedCornerShape(10.dp),
                    colors = CardDefaults.cardColors(
                        Color(0xFF303030),
                    ),
                ) {
                    IconButton(
                        onClick = { /*TODO*/ },
                        colors = IconButtonDefaults.iconButtonColors(
                            Color(0xFF303030),
                        ),
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Icon(
                            Icons.Filled.ArrowForwardIos,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                }
            }
            Row(
                modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Total:",
                    fontSize = 20.sp,
                    fontWeight = FontWeight(700),
                    fontFamily = GoogleFont.NunitoSansFont
                )
                Text(
                    text = "$priceTotal",
                    fontSize = 20.sp,
                    fontWeight = FontWeight(700),
                    fontFamily = GoogleFont.NunitoSansFont
                )
            }
            Button(
                onClick = { navController.navigate("checkout") }, shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                colors = ButtonDefaults.buttonColors(Color(0xFF242424))
            ) {
                Text(
                    text = "Check out",
                    fontSize = 20.sp,
                    fontWeight = FontWeight(600),
                    fontFamily = GoogleFont.NunitoSansFont
                )
            }
        }
    }
}

