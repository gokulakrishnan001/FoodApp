package com.example.foodapp.screens

import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.foodapp.R
import com.example.foodapp.ui.theme.LittleLemonColor


@Composable
@ExperimentalMaterial3Api
fun OnBoardScreen(navController: NavHostController) {

    var firstName by remember {
        mutableStateOf("")
    }

    var lastName by remember {
        mutableStateOf("")
    }

    var email by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
          Spacer(modifier = Modifier.width(85.dp))
            Image(
                painter = painterResource(id = R.drawable.littlelemonimgtxt_nobg),
                contentDescription = "Little Lemon Logo",
                alignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth(0.5F)
                    .padding(horizontal = 20.dp, vertical = 10.dp)
                    .align(Alignment.CenterVertically)

            )
        }
        Text(
            text = "Let's Get To Know You",
            modifier = Modifier
                .fillMaxWidth()
                .background(LittleLemonColor.green)
                .height(75.dp)
                .align(Alignment.CenterHorizontally)
                .padding(20.dp),
            color = Color.White,
            textAlign = TextAlign.Center,
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Personal Information",
            modifier = Modifier.padding(20.dp),
            color = Color.Black
        )
        OutlinedTextField(value = firstName, onValueChange = {
            firstName = it
        },
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.Black,
                unfocusedContainerColor = Color.White,
                focusedContainerColor = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            shape = RoundedCornerShape(10.dp),
            label = {
                Text("First Name")
            }
        )
        OutlinedTextField(value = lastName, onValueChange = {
            lastName = it
        },
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.Black,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            shape = RoundedCornerShape(10.dp),
            label = {
                Text("Last Name")
            }
        )

        OutlinedTextField(value = email, onValueChange = {
            email = it
        },
            colors = TextFieldDefaults.colors(
              focusedTextColor = Color.Black,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            shape = RoundedCornerShape(10.dp),
            label = {
                Text("Enter Email")
            }
        )

        val context = LocalContext.current
        Spacer(modifier = Modifier.height(80.dp))
        Button(
            onClick = {
                val sharedPreferences: SharedPreferences =
                    context.getSharedPreferences("RegistrationDetails", Context.MODE_PRIVATE)

                if (firstName.isNotBlank() && lastName.isNotBlank() && email.isNotBlank()) {
                    val editor = sharedPreferences.edit()
                    editor.putString("FirstName", firstName);
                    editor.putString("LastName", lastName);
                    editor.putString("Email", email);
                    editor.apply()
                    Toast.makeText(context, "Registration Successful", Toast.LENGTH_SHORT).show()
                    navController.navigate(Home.route)
                } else {
                    Toast.makeText(context, "Enter All Fields", Toast.LENGTH_LONG).show()
                }
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
            Text("Register")
        }


    }
}



