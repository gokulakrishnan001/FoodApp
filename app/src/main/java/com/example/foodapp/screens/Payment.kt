package com.example.foodapp.screens

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.foodapp.R
import com.example.foodapp.ui.theme.LittleLemonColor
import kotlinx.coroutines.delay


@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun PaymentScreen(
amount:String,
navController: NavController
) {

    Scaffold(
        containerColor = Color.White,
        topBar = {
            TopAppBar(title = { Text("Payment")},
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = LittleLemonColor.cloud,
                    titleContentColor = LittleLemonColor.green
                ),
                navigationIcon = {
                    IconButton(onClick = {
                        //navController.navigate(Home.route)
                    }) {
                        Icon(Icons.Filled.ArrowBackIosNew,"")
                    }
                }
            )

        },
        bottomBar = {
            Button(onClick = {
navController.navigate(Payment.route)
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
                Text("Processed to Pay $${amount}")
            }
        }
    ) {


        Column (
            modifier = Modifier.padding(it)
        ){
            Button(
                onClick = { /*TODO*/ },

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black
                ),
                shape = RectangleShape,
                border = BorderStroke(
                    1.dp,
                    Color.Blue
                )

            ) {
                Image(painter = painterResource(id = R.drawable.gpay), contentDescription = "")
                Text(" PAY Via G-PAY")
            }
            Spacer(modifier = Modifier.height(10.dp))
            HorizontalDivider(
                color = LittleLemonColor.yellow,
                thickness = 5.dp
            )
            Text("                                Or")
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.padding(start = 60.dp, top = 10.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img1), contentDescription = "",
                    modifier = Modifier.size(80.dp, 80.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.img2), contentDescription = "",
                    modifier = Modifier.size(80.dp, 80.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.img3), contentDescription = "",
                    modifier = Modifier.size(80.dp, 80.dp)
                )


            }

            Text("Payment Amount ",
                modifier = Modifier.padding(10.dp)
                )

            Text("$${amount}",
                style = TextStyle(
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(start=10.dp,top=5.dp)
            )


            OutlinedTextField(
                value = "Card Name", onValueChange = {},
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color.Black,
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                shape = RoundedCornerShape(10.dp),
            )
            OutlinedTextField(
                value = "XXXX-XXXX-XXXX-XXXX", onValueChange = {},
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color.Black,
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                shape = RoundedCornerShape(10.dp),
                trailingIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.img1), contentDescription = "",
                        modifier = Modifier.size(40.dp, 40.dp)
                    )

                }
            )
          Row {
              OutlinedTextField(
                  value = "EXP MM/YY", onValueChange = {},
                  colors = TextFieldDefaults.colors(
                      focusedTextColor = Color.Black,
                      focusedContainerColor = Color.White,
                      unfocusedContainerColor = Color.White
                  ),
                  modifier = Modifier
                      .fillMaxWidth(0.7f)
                      .padding(7.dp),
                  shape = RoundedCornerShape(10.dp),
              )
              OutlinedTextField(
                  value = "CVV", onValueChange = {},
                  colors = TextFieldDefaults.colors(
                      focusedTextColor = Color.Black,
                      focusedContainerColor = Color.White,
                      unfocusedContainerColor = Color.White
                  ),
                  modifier = Modifier
                      .fillMaxWidth(0.2f)
                      .weight(0.1F)
                      .height(70.dp)
                      .padding(7.dp),
                  shape = RoundedCornerShape(10.dp),

              )
          }
        }
    }
}


@Composable
fun PaymentSucessful(
    navController: NavController
){
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {

        val context= LocalContext.current
        LaunchedEffect(key1 = true) {
            delay(3000L)
            navController.navigate(Home.route)
        }
        Image(painter = painterResource(id = R.drawable.payment_done), contentDescription ="",
            contentScale = ContentScale.Fit,
            modifier = Modifier.size(150.dp,150.dp)
            )
        Text("Payment Successful",
            modifier = Modifier.padding(10.dp),
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            )

        Text("You Automatically redirect into few seconds",
            modifier = Modifier.padding(10.dp),
            style = TextStyle(
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold
            )
        )


    }
}