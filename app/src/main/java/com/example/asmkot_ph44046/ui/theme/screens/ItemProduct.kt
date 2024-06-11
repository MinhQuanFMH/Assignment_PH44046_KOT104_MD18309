package com.example.asmkot_ph44046.ui.screens

import android.content.Context
import android.widget.Toast
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.RemoveCircleOutline
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.asmkot_ph44046.R
import com.example.asmkot_ph44046.database.MyDatabase
import com.example.asmkot_ph44046.database.entities.Carts
import com.example.asmkot_ph44046.database.entities.Products
import com.example.asmkot_ph44046.database.factory.CartFactory
import com.example.asmkot_ph44046.database.factory.ProductFactory
import com.example.asmkot_ph44046.database.factory.SaveUserFactory
import com.example.asmkot_ph44046.database.repository.CartRepository
import com.example.asmkot_ph44046.database.repository.ProductRepository
import com.example.asmkot_ph44046.database.repository.SaveUserRepository
import com.example.asmkot_ph44046.database.viewModel.CartViewModel
import com.example.asmkot_ph44046.database.viewModel.ProductViewModel
import com.example.asmkot_ph44046.database.viewModel.SaveUserViewModel
import com.example.asmkot_ph44046.font.GoogleFont
import com.example.asmkot_ph44046.ui.component.LoadingScreen

@Composable
fun ItemProduct(navController: NavController, id: String) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val cartRepository = CartRepository(
        MyDatabase.getDatabase(context, scope).cartDao()
    )
    val cartViewModel: CartViewModel = viewModel(factory = CartFactory(cartRepository))

    val userRepository =
        ProductRepository(
            MyDatabase.getDatabase(context = context, scope = scope).productDao()
        )
    val productViewModel: ProductViewModel = viewModel(factory = ProductFactory(userRepository))

    val saveUserRepository =
        SaveUserRepository(MyDatabase.getDatabase(context, scope).saveUserDao())
    val saveUserViewModel: SaveUserViewModel =
        viewModel(factory = SaveUserFactory(saveUserRepository))

    ComponentItemProduct(
        navController = navController,
        id = id,
        cartViewModel = cartViewModel,
        productViewModel = productViewModel,
        saveUserViewModel = saveUserViewModel,
        context = context
    )
}

@Composable
private fun ComponentItemProduct(
    navController: NavController,
    id: String,
    cartViewModel: CartViewModel,
    productViewModel: ProductViewModel,
    saveUserViewModel: SaveUserViewModel,
    context: Context
) {
    val itemProduct by productViewModel.getItemProduct(id.toInt()).observeAsState()

    when {
        itemProduct != null -> ItemProductView(
            navController = navController,
            products = itemProduct!!,
            cartViewModel = cartViewModel,
            saveUserViewModel = saveUserViewModel,
            context = context
        )

        else -> LoadingScreen()
    }

}

@Composable
private fun ItemProductView(
    modifier: Modifier = Modifier,
    navController: NavController,
    products: Products,
    cartViewModel: CartViewModel,
    saveUserViewModel: SaveUserViewModel,
    context: Context
) {

    val scrollSate = rememberScrollState()

    val saveUser by saveUserViewModel.getUser.observeAsState(
        null
    )

    var counts by remember {
        mutableIntStateOf(1)
    }

    Column(
        modifier
            .fillMaxSize()
            .verticalScroll(scrollSate)
    ) {
        Column {
            Box(
                modifier
                    .height(450.dp)
                    .fillMaxWidth()
            ) {
                Box(
                    modifier
                        .fillMaxHeight()
                        .fillMaxWidth(0.96f)
                        .align(Alignment.TopEnd)
                ) {
                    Card(
                        shape = RoundedCornerShape(0.dp, 0.dp, 0.dp, 30.dp),
                        modifier = Modifier.align(Alignment.TopEnd),
                    ) {
                        AsyncImage(
                            model = products.imgProduct,
                            contentDescription = null,
                            modifier
                                .fillMaxWidth(0.9f),
                            contentScale = ContentScale.Crop
                        )
                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Spacer(modifier = Modifier.height(45.dp))
                        Card(
                            shape = RoundedCornerShape(6.dp),
                            colors = CardDefaults.cardColors(Color.White),
                            elevation = CardDefaults.elevatedCardElevation()
                        ) {
                            IconButton(
                                onClick = {
                                    navController.popBackStack()
                                },
                                modifier.size(40.dp)
                            ) {
                                Icon(Icons.Filled.KeyboardArrowLeft, contentDescription = null)
                            }
                        }
                        Spacer(modifier.height(50.dp))
                        Card(
                            shape = RoundedCornerShape(40.dp),
                            modifier = Modifier
                                .height(220.dp)
                                .width(65.dp),
                            colors = CardDefaults.cardColors(Color.White),
                            elevation = CardDefaults.elevatedCardElevation()
                        ) {
                            Row(
                                modifier.fillMaxSize(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Image(
                                    painterResource(id = R.drawable.cicle_it_product),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .height(195.dp)
                                        .width(65.dp)
                                )
                            }
                        }
                    }
                }
            }
            Column(modifier.padding(20.dp)) {
                Spacer(modifier.height(10.dp))
                Text(
                    text = products.nameProduct,
                    fontFamily = GoogleFont.GelasioFont,
                    fontSize = 24.sp,
                    fontWeight = FontWeight(500),
                    color = Color(0xFF303030)
                )
                Spacer(modifier.height(10.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "${products.priceProduct} $",
                        fontFamily = GoogleFont.NunitoSansFont,
                        fontSize = 30.sp,
                        fontWeight = FontWeight(700),
                        color = Color(0xFF303030)
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Card(
                            modifier.size(30.dp),
                            shape = RoundedCornerShape(5.dp),
                            colors = CardDefaults.cardColors(
                                Color(0xFFE0E0E0)
                            )
                        ) {
                            IconButton(onClick = { counts++ }) {
                                Icon(Icons.Default.Add, contentDescription = null)
                            }
                        }
                        Spacer(modifier.width(10.dp))
                        Text(
                            text = "$counts", fontFamily = GoogleFont.NunitoSansFont,
                            fontSize = 18.sp,
                            fontWeight = FontWeight(600),
                            color = Color(0xFF242424)
                        )
                        Spacer(modifier.width(10.dp))
                        Card(modifier.size(30.dp), shape = RoundedCornerShape(5.dp)) {
                            IconButton(onClick = { counts-- }) {
                                Icon(Icons.Filled.RemoveCircleOutline, contentDescription = null)
                            }
                        }
                    }
                }
                Spacer(modifier.height(10.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painterResource(id = R.drawable.star_it),
                        contentDescription = null,
                        modifier = Modifier.size(20.dp),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier.width(10.dp))
                    Text(
                        text = "${products.rate}",
                        fontFamily = GoogleFont.NunitoSansFont,
                        fontSize = 18.sp,
                        fontWeight = FontWeight(700),
                        color = Color(0xFF303030)
                    )
                    Spacer(modifier.width(20.dp))
                    Text(
                        text = "(50 reviews)",
                        fontFamily = GoogleFont.NunitoSansFont,
                        fontSize = 18.sp,
                        fontWeight = FontWeight(600),
                        color = Color(0xFF808080)
                    )
                }
                Spacer(modifier.height(10.dp))

                Text(
                    text = "Minimal Stand is made of by natural wood. The design that is very simple and minimal. This is truly one of the best furnitures in any family for now. With 3 different colors, you can easily select the best match for your home.",
                    fontFamily = GoogleFont.NunitoSansFont,
                    fontSize = 14.sp,
                    fontWeight = FontWeight(300),
                    color = Color(0xFF606060)
                )

                Spacer(modifier.height(10.dp))
                Row(
                    modifier
                        .height(60.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Card(
                        colors = CardDefaults.cardColors(Color(0xFFF0F0F0)),
                        modifier = Modifier.width(60.dp)
                    ) {
                        Row(
                            modifier.fillMaxSize(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            // add to favorite
                            IconButton(onClick = { /*TODO*/ }) {
                                Icon(Icons.Outlined.Favorite, contentDescription = null)
                            }
                        }
                    }
                    Card(
                        shape = RoundedCornerShape(6.dp),
                        colors = CardDefaults.cardColors(
                            Color(0xFF242424)
                        )
                    ) {
                        // add to cart
                        Button(
                            onClick = {
                                when {
                                    saveUser != null -> cartViewModel.addToCart(
                                        Carts(
                                            id = 0,
                                            email = saveUser!!.email,
                                            idProduct = products.id,
                                            nameProduct = products.nameProduct,
                                            imgProduct = products.imgProduct,
                                            priceProduct = products.priceProduct,
                                            quantity = counts
                                        )
                                    )

                                    else -> Toast.makeText(context, "Error!", Toast.LENGTH_SHORT)
                                        .show()
                                }

                            },
                            modifier
                                .fillMaxWidth(0.9f)
                                .height(60.dp),
                            colors = ButtonDefaults.buttonColors(
                                Color(0xFF242424)
                            )
                        ) {
                            Text(
                                text = "Add to cart",
                                fontFamily = GoogleFont.NunitoSansFont,
                                fontSize = 20.sp,
                                fontWeight = FontWeight(600),
                                color = Color.White
                            )
                        }
                    }
                }
            }
        }
    }
}
