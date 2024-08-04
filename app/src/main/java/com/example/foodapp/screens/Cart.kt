package com.example.foodapp.screens

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Dangerous
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.foodapp.ui.theme.LittleLemonColor

@OptIn(ExperimentalMaterial3Api::class, ExperimentalSharedTransitionApi::class,
    ExperimentalGlideComposeApi::class
)
@Composable
fun SharedTransitionScope.CartScreen(
    amount:String,
    image:String,
    title:String,
    quantity:String,
    animatedVisibilityScope: AnimatedVisibilityScope,
    navController: NavController
){
  Scaffold(
      containerColor = Color.White,
      topBar = {
          TopAppBar(title = { Text("Your Cart")},
              colors = TopAppBarDefaults.topAppBarColors(
                  containerColor = Color.White,
                  titleContentColor = LittleLemonColor.green
              ),
              navigationIcon = {
                  IconButton(onClick = {
                      navController.navigate(Home.route)
                  }) {
                      Icon(Icons.Filled.ArrowBackIosNew,"")
                  }
              }
              )

      },
      bottomBar = {
          Button(onClick = {
          navController.navigate("payment_screen/$amount")
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
              Text("Place your Order")
          }
      }
  ) {
      Spacer(modifier = Modifier.height(20.dp))


       Column(
           modifier = Modifier.padding(it)
       ) {
          Card(
              modifier = Modifier.padding(10.dp),
              colors = CardDefaults.cardColors(
                  containerColor = LittleLemonColor.cloud
              )

          ) {
              ListItem(
                  colors = ListItemDefaults.colors(
                      containerColor = LittleLemonColor.cloud
                  ),
                  headlineContent = {
                  Text(title,
                      color = LittleLemonColor.charcoal,
                      modifier = Modifier.sharedElement(
                          state = rememberSharedContentState(key = "image/$title"),
                          animatedVisibilityScope = animatedVisibilityScope,
                          boundsTransform = { _, _ ->
                              tween(durationMillis = 1500)
                          }
                      )
                      )
              },
                  leadingContent = {
                      GlideImage(model = image, contentDescription = "",
                          contentScale = ContentScale.Fit,
                          modifier = Modifier
                              .size(
                                  width = 120.dp,
                                  height = 100.dp
                              )
                              .padding(5.dp)
                              .sharedElement(
                                  state = rememberSharedContentState(key = "image/$image"),
                                  animatedVisibilityScope = animatedVisibilityScope,
                                  boundsTransform = { _, _ ->
                                      tween(durationMillis = 1500)

                                  }
                              )
                          )
                  },
                  supportingContent = {
                    Column {
                        Text("$${amount}",
                            color = Color.Black
                            )
                        Text("Quantity $quantity",
                            color = Color.Black
                            )
                    }
                  },
                  trailingContent = {
                      Icon(Icons.Filled.Dangerous, contentDescription = "",
                          modifier =
                              Modifier.padding(10.dp),
                          tint = Color.Black
                          )
                  }
                  )
          }

       }

  }
}