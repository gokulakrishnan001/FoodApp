package com.example.foodapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.foodapp.R
import com.example.foodapp.database.MenuItemRoom
import com.example.foodapp.ui.theme.LittleLemonColor

@Composable
fun Home(navController: NavHostController, databaseMenuItem: List<MenuItemRoom>) {

    Column (
        modifier = Modifier.fillMaxSize()
    ){
        TopAppBar(navController)
        UpperPanel()
        OrderView(databaseMenuItem)

    }
}


@Composable
fun TopAppBar(navController: NavHostController) {
    Row(horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically) {

        Image(
            painter = painterResource(id = R.drawable.littlelemonimgtxt_nobg),
            contentDescription = "Little Lemon Logo",
            modifier = Modifier
                .fillMaxWidth(0.5F)
                .padding(horizontal = 20.dp, vertical = 10.dp)
        )
        IconButton(onClick = {
            navController.navigate(Profile.route)
        }) {
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "Cart",
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Composable
fun UpperPanel() {
    Column(
        modifier = Modifier
            .background(LittleLemonColor.green)
            .height(350.dp)
            .padding(start = 10.dp, end = 12.dp, top = 10.dp, bottom = 10.dp),
        verticalArrangement = Arrangement.SpaceAround

    ) {
        Text(
            text = stringResource(id = R.string.title),
            fontSize = 35.sp,
            fontWeight = FontWeight.Bold,
            color = LittleLemonColor.yellow
        )
        Text(
            text = stringResource(id = R.string.location),
            fontSize = 20.sp,
            color = LittleLemonColor.cloud
        )
        Row(
            horizontalArrangement = Arrangement.SpaceAround,

            modifier = Modifier
                .padding(top = 20.dp)
        ) {
            Text(
                text = stringResource(id = R.string.description),
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .padding(bottom = 28.dp, end = 20.dp)
                    .fillMaxWidth(0.6f),
                color = LittleLemonColor.cloud

            )
            Image(
                painter = painterResource(id = R.drawable.upperpanelimage),
                contentDescription = "Upper Panel Image",
                modifier = Modifier.clip(RoundedCornerShape(10.dp))
            )
        }

      OutlinedTextField(value = "", onValueChange = {},
          modifier = Modifier
              .fillMaxWidth()
              .padding(1.dp)
              .background(Color.White)
          ,
          leadingIcon = {
              Icon(imageVector = Icons.Default.Search, contentDescription ="" )
          },
          placeholder = {
              Text(text = "Enter Search Phrase", color = Color.Black)
          }
          )

    }
}



@Composable
fun OrderView(databaseMenuItem: List<MenuItemRoom>) {
    var buttonState1 by remember {
        mutableStateOf(false)
    }
    var buttonState2 by remember {
        mutableStateOf(false)
    }
    var buttonState3 by remember {
        mutableStateOf(false)
    }
    var buttonState4 by remember {
        mutableStateOf(false)
    }





    var menuItems by remember {
        mutableStateOf(databaseMenuItem);
    }

    Column {
        Text(text = "ORDER FOR DELIVERY",
            style =MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 15.dp),
            fontSize = 15.sp
            )

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            Button(onClick = {
              buttonState1=true
                menuItems= databaseMenuItem.filter {
                    it.category == "starters"
                }
                },
                enabled = true,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.LightGray,
                    disabledContainerColor = Color.LightGray,
                    contentColor = LittleLemonColor.charcoal
                )
                ,
       modifier = Modifier.padding(start=10.dp,end = 10.dp)

                ) {
                Text("Starters",
                    color = Color.Black,
                    fontSize = 15.sp
                    )
            }
            Button(onClick = {
                             buttonState2=true
               menuItems= databaseMenuItem.filter {
                    it.category == "mains"
                }

            },
                modifier = Modifier.padding(end = 10.dp),
                enabled = true,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.LightGray,
                    disabledContainerColor = Color.LightGray,
                    contentColor = LittleLemonColor.charcoal
                ),


                ) {
                Text("Mains")
            }
            Button(onClick = {
                             buttonState3=true
                menuItems= databaseMenuItem.filter {
                    it.category == "desserts"
                }
            },
                modifier = Modifier.padding(end = 10.dp),
                enabled = true,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.LightGray,
                    disabledContainerColor = Color.LightGray,
                    contentColor = LittleLemonColor.charcoal
                ),

                ) {
                Text("Desserts")
            }

        }
        LowerPanel(dish =menuItems)
    }
}


@Composable
fun LowerPanel(dish:List<MenuItemRoom>) {
    Column {
        LazyColumn {
            itemsIndexed(dish) { _, dishes ->
               MenuDish(menuItemNetwork = dishes)
            }
            }
        }
    }

@OptIn(ExperimentalMaterial3Api::class, ExperimentalGlideComposeApi::class)
@Composable
fun MenuDish(menuItemNetwork: MenuItemRoom) {
        Row(
            modifier= Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(8.dp)
        ){
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.75f)
                    .padding(top = 5.dp, bottom = 5.dp)
            ){
                Text(text = menuItemNetwork.title,
                    style =MaterialTheme.typography.bodyLarge

                )
                Text(text = menuItemNetwork.description, style = MaterialTheme.typography.bodyMedium)
                Text(text = "$${menuItemNetwork.price}",style = MaterialTheme.typography.bodySmall)

            }
            GlideImage(model = menuItemNetwork.image, contentDescription = "",
                modifier = Modifier.clip(RoundedCornerShape(10.dp)
                )
            )
        }

    Divider(
        modifier = Modifier.padding(start = 8.dp, end = 8.dp),
        thickness = 1.dp,
        color = LittleLemonColor.yellow
    )
}
