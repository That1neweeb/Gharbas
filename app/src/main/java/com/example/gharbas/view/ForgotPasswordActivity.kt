package com.example.gharbas.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gharbas.R
import com.example.gharbas.repository.UserRepoImpl
import com.example.gharbas.ui.theme.White
import com.example.gharbas.ui.theme.lightYellow
import com.example.gharbas.view.ui.theme.GharbasTheme
import com.example.gharbas.viewmodel.UserViewModel

class ForgotPasswordActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
                ForgotPasswordBody()
            }
        }
    }

@Composable
fun ForgotPasswordBody() {
    val UserViewModel = remember { UserViewModel(UserRepoImpl()) }
    val context = LocalContext.current
    val activity = context as Activity

    var email by remember { mutableStateOf("") }
    Scaffold() {
        padding ->
        Column(modifier = Modifier.padding(padding)
            .fillMaxSize()
            .background(White),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Row(modifier = Modifier.fillMaxWidth()
                .padding(top = 100.dp, bottom = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center) {
                Image(
                    painter = painterResource(R.drawable.applogo),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.padding(bottom = 10.dp)
                        .size(120.dp)


                )
            }
            Column(
                modifier = Modifier.padding(vertical = 20.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(
                value = email,
                onValueChange = { data ->
                    email = data
                },
                placeholder = {
                    Text("abc@gmail.com")
                },
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp)
                    )
            }
                Button(onClick = {
                    UserViewModel.forgetPassword(email){
                        success, message ->
                        if (success){
                            Toast.makeText(context, "Reset email sent", Toast.LENGTH_SHORT).show()
                            val intent = Intent(context,LoginScreen::class.java)
                            context.startActivity(intent)
                            activity.finish()
                        }
                        else{
                            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                        }

                    }
                },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = lightYellow,
                        contentColor = White
                    )
                ) {
                    Text("Send Reset email")
                }




        }
    }
}

@Composable
@Preview
fun ForgotPasswordPreview() {
    ForgotPasswordBody()
}