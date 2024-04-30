package com.example.foodapp.screens

import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.foodapp.R
import com.example.foodapp.ui.theme.LittleLemonColor

@Composable
fun Profile(navController: NavHostController) {

    val context= LocalContext.current
    val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("RegistrationDetails", Context.MODE_PRIVATE)



    var firstName by remember {
        mutableStateOf("")
    }

    firstName= sharedPreferences.getString("FirstName","").toString()


    var lastName by remember {
        mutableStateOf("")
    }

    lastName=sharedPreferences.getString("LastName", "").toString()

    var email by remember {
        mutableStateOf("")
    }

    email=sharedPreferences.getString("Email","").toString()
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

                modifier = Modifier
                    .fillMaxWidth(0.5F)
                    .size(70.dp)
                    .padding(horizontal = 20.dp, vertical = 10.dp)
                    .align(Alignment.CenterVertically)


            )
        }

        Spacer(modifier = Modifier.height(50.dp))
        Text(
            text = "Personal Information",
            modifier = Modifier.padding(20.dp),
            color = Color.Black
        )
        OutlinedTextField(value = firstName, onValueChange = {
            firstName = it
        },
            enabled = false,
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.Black,
                disabledTextColor = Color.Black,
                disabledContainerColor = Color.White
                ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            shape = RoundedCornerShape(10.dp),
            label = {
                Text("First Name",
                    color = Color.Black
                    )
            }
        )
        OutlinedTextField(value = lastName, onValueChange = {
            lastName = it
        },
            enabled = false,
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.Black,
                disabledTextColor = Color.Black,
                disabledContainerColor = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            shape = RoundedCornerShape(10.dp),
            label = {
                Text("Last Name",
                    color = Color.Black
                    )
            }
        )
        OutlinedTextField(value = email, onValueChange = {
            email = it
        },
            enabled = false,
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.Black,
                disabledTextColor = Color.Black,
                disabledContainerColor = Color.White
            ),

            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            shape = RoundedCornerShape(10.dp),
            label = {
                Text("Your Email",
                    color = Color.Black
                    )
            }
        )
        Spacer(modifier = Modifier.height(70.dp))

        Button(onClick = {
            val sharedPreferencesRemove: SharedPreferences =
                context.getSharedPreferences("RegistrationDetails", Context.MODE_PRIVATE)
         sharedPreferencesRemove.edit().remove("FirstName").apply()
            sharedPreferencesRemove.edit().remove("LastName").apply()
            sharedPreferencesRemove.edit().remove("Email").apply()
            Toast.makeText(context,"Log out",Toast.LENGTH_SHORT).show()
         navController.navigate(OnBoard.route)
        },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
            ,
            colors = ButtonDefaults.buttonColors(
                containerColor = LittleLemonColor.yellow,
                contentColor = Color.Black
            ),
            shape = RectangleShape,
            border = BorderStroke(
                1.dp,
                Color.White
            )

        ) {
            Text("LogOut")
        }
    }
}
