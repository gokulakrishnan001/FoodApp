package com.example.foodapp.screens

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Minimize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.foodapp.R
import com.example.foodapp.ui.theme.LittleLemonColor
import kotlinx.coroutines.launch
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@OptIn(ExperimentalGlideComposeApi::class, ExperimentalSharedTransitionApi::class,
    ExperimentalMaterial3Api::class
)
@Composable
fun SharedTransitionScope.ExpandFoodScreen(
    image:String,
    title:String,
    description:String,
    price:String,
    category:String,
    animatedVisibilityScope: AnimatedVisibilityScope,
    navController: NavController
) {
    val snackbarHostState = remember {
        SnackbarHostState()
    }
    val scope = rememberCoroutineScope()

    Scaffold(

        containerColor = Color.White,
        topBar = {
            TopAppBar(
                title = {
                    Image(
                        painter = painterResource(id = R.drawable.littlelemonimgtxt_nobg),
                        contentDescription = "Little Lemon Logo",
                        modifier = Modifier
                            .fillMaxWidth(0.8F)
                            .padding(horizontal = 10.dp, vertical = 10.dp)
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate(Home.route)
                    }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBackIosNew, contentDescription = "",
                            tint = Color.Black
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                )
            )
        },
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        }
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(it),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(30.dp))
            Card(
                colors = CardDefaults.cardColors(containerColor = LittleLemonColor.green),
                modifier = Modifier.padding(10.dp)
            ) {
                Column(Modifier.padding(10.dp)) {
                    GlideImage(model = image,
                        contentDescription = "",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier.sharedElement(
                            state = rememberSharedContentState(key = "image/$image"),
                            animatedVisibilityScope = animatedVisibilityScope,
                            boundsTransform = { _, _ ->
                                tween(durationMillis = 1500)
                            }
                        )
                    )
                    Text("                   $title",
                        color = LittleLemonColor.yellow,
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.CenterHorizontally)
                            .sharedElement(
                                state = rememberSharedContentState(key = "image/$title"),
                                animatedVisibilityScope = animatedVisibilityScope,
                                boundsTransform = { _, _ ->
                                    tween(durationMillis = 1500)

                                }
                            )
                    )
                }
            }
            Text(
                text = "Ingredients",
                style = TextStyle(
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(10.dp)
            )
            Text(text = description, modifier = Modifier.padding(10.dp))

            Text(
                text = "Amount Payable",
                style = TextStyle(
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(10.dp)
            )

            var priceValue by remember {
                mutableIntStateOf(price.toInt())
            }
            
            var snackbarState by remember {
                mutableStateOf(false)
            }
            


            var quantity by remember {
                mutableIntStateOf(1)
            }
            Card(
                colors = CardDefaults.cardColors(containerColor = LittleLemonColor.green),
                modifier = Modifier
                    .padding(10.dp)
                    .wrapContentSize(Alignment.Center)
                    .fillMaxWidth(0.7f)

            ) {

                Row(
                    horizontalArrangement = Arrangement.Absolute.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        "       $$priceValue",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(10.dp),
                        color = LittleLemonColor.yellow
                    )
                    IconButton(onClick = {
                        quantity += 1
                        priceValue = price.toInt() * quantity
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Add, contentDescription = "",
                            tint = Color.White
                        )
                    }

                    Text(
                        quantity.toString(),
                        modifier = Modifier.padding(10.dp),
                        color = LittleLemonColor.yellow
                    )

                    IconButton(onClick = {
                        quantity -= 1
                        priceValue = price.toInt() * quantity
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Minimize, contentDescription = "",
                            tint = Color.White,
                            modifier = Modifier.padding(bottom = 10.dp)
                        )
                    }
                }



                }
            Spacer(modifier = Modifier.height(30.dp))
            var openDialog by remember {
                mutableStateOf(false)
            }
            Button(
                onClick = {
                  openDialog=true
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = LittleLemonColor.yellow,
                    contentColor = Color.Black
                ),
                shape = RectangleShape,
                border = BorderStroke(
                    1.dp,
                    Color.Yellow
                )
            ) {
                Text("Add To Cart")
            }




            if (openDialog) {
                val total = price.toInt() * quantity
                val discount=quantity*0.5
                val pay=total - discount

                AlertDialog(
                    onDismissRequest = {
                        openDialog = false
                    },
                    containerColor = LittleLemonColor.cloud,
                    textContentColor = LittleLemonColor.charcoal,
                    titleContentColor = LittleLemonColor.green,

                    title = {
                        Text(text = "Payment")
                    },
                    text = {
                        Column {
                            
                            Text("Food Price: $$price")
                            Text("Quantity: $quantity")

                            Text("Total Amount: $total")
                            Text("Discount Amount: $$discount")
                            Text("Final payment: $$pay")
                        }
                    },
                    confirmButton = {
                        TextButton(

                            onClick = {
                                openDialog = false
                                val encodedImage = URLEncoder.encode(image, StandardCharsets.UTF_8.toString())
                                scope.launch {
                                  val snackbar=  snackbarHostState.showSnackbar(
                                        "Food is Added into Cart ",
                                      actionLabel = "View Cart",
                                      duration = SnackbarDuration.Long
                                    )
                                    when (snackbar) {
                                        SnackbarResult.Dismissed -> {}

                                        SnackbarResult.ActionPerformed ->{
                                            navController.navigate("cart_screen/$encodedImage/$title/$quantity/$pay")
                                        }
                                    }
                                }
                                
                            }) {
                            Text("Confirm Order",
                                color = LittleLemonColor.charcoal
                                )
                        }
                    },
                    dismissButton = {
                        TextButton(
                            onClick = {
                                openDialog = false
                            }) {
                            Text("Cancel Order",
                                color = Color.LightGray
                                )
                        }
                    }
                )
            }

        }

    }
}





