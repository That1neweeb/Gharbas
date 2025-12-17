package com.example.gharbas.view

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gharbas.R
import com.example.gharbas.repository.UserRepoImpl
import com.example.gharbas.ui.theme.Black
import com.example.gharbas.ui.theme.Blue
import com.example.gharbas.ui.theme.GharbasTheme
import com.example.gharbas.ui.theme.Grey
import com.example.gharbas.ui.theme.LightGrey
import com.example.gharbas.ui.theme.White
import com.example.gharbas.ui.theme.lightYellow
import com.example.gharbas.viewmodel.UserViewModel
import kotlin.jvm.java

class LoginScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GharbasTheme {
                    LoginBody()
            }
        }
    }
}

@Composable
fun LoginBody(){
    val UserViewModel = remember { UserViewModel(UserRepoImpl()) }

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var visibility by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val activity = context as Activity
    val sharedPreferences = context.getSharedPreferences("User", Context.MODE_PRIVATE)

    val localEmail: String? = sharedPreferences.getString("email", "")
    val localPassword: String? = sharedPreferences.getString("password", "")


Scaffold{
    padding ->
    Column(modifier = Modifier.padding(padding)
        .fillMaxSize()
        .background(colorResource(R.color.white))

    ) {
        Spacer(modifier = Modifier.padding(top = 150.dp, bottom = 30.dp)
            .fillMaxWidth())

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center

        ) {
            Image(
                painter = painterResource(R.drawable.applogo),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(100.dp)
                    .padding(bottom = 10.dp),
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically


        ) {
            Text(
                "Welcome to GharBas, Login to get Started",
                modifier = Modifier.fillMaxWidth()
                    .padding(vertical = 10.dp),
                color = lightYellow,
                textAlign = TextAlign.Center,
                fontSize = 24.sp
            )
        }
//        INPUT FIELDS
        OutlinedTextField(
            value = email,
            onValueChange = { data ->
                email = data
            },
            placeholder = {
                Text("abc@gmail.com")
            },
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = White,
                focusedContainerColor = LightGrey,
                focusedIndicatorColor = Blue,
                unfocusedIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp),
        )

        OutlinedTextField(
            value = password,
            onValueChange = { data ->
                password = data
            },
            placeholder = {
                Text("*******")
            },
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = White,
                focusedContainerColor = LightGrey,
                focusedIndicatorColor = Blue,
                unfocusedIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp),
            visualTransformation = if (visibility) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = { visibility = !visibility }) {
                    Icon(
                        painter = if (visibility)
                            painterResource(com.example.gharbas.R.drawable.baseline_visibility_off_24)
                        else painterResource(
                            com.example.gharbas.R.drawable.baseline_visibility_24
                        ),
                        contentDescription = null
                    )

                }
            }
        )
//        FORGOT PASSWORD
        Row(modifier = Modifier.padding(horizontal = 10.dp)
            .fillMaxWidth()) {
            Text(
                "Forgot Password ?", style = TextStyle(
                    textAlign = TextAlign.End,
                    color = Color.Gray.copy(0.8f)
                ),
                modifier = Modifier.fillMaxWidth()
                    .padding(end = 20.dp)
            )
        }

//            Login Button
            Row {
                Button(
                    onClick ={
                        UserViewModel.login(email, password) {
                                success, message ->
                            if(success) {
//                                val intent = Intent(context, DashboardActivity::class.java  )
//                                context.startActivity(intent)
//                                activity.finish()
                                Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT).show()
                            } else {
                                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                            }
                        }


                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = lightYellow,
                        contentColor = White
                    ),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .padding(horizontal = 10.dp)
                        .height(height = 60.dp)
                        .width(width = 375.dp)
                ) {
                    Text(text = "Log In", style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp), color = Color.White,)
                }
            }
            //Sign Up section
            Spacer(modifier = Modifier.height(12.dp))
            Row() {
                Text(text = "Don't have account?",style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 15.sp),
                    modifier = Modifier.padding(start = 20.dp))
                Text(text = "  Sign Up", color = Color.Blue, fontWeight = FontWeight.Bold, fontSize = 15.sp, modifier = Modifier.clickable{
                    val intent = Intent(
                        context,
                        RegisterActivity::class.java
                    )

                    context.startActivity(intent)
                    activity.finish()
                })
            }



    }
}
}

@Preview
@Composable
fun LoginPreview(){
    LoginBody()
}

