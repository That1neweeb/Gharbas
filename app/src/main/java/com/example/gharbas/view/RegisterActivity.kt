package com.example.gharbas.view

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.icu.util.Calendar
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.R
import com.example.gharbas.model.UserModel
import com.example.gharbas.repository.UserRepoImpl
import com.example.gharbas.ui.theme.Blue
import com.example.gharbas.ui.theme.Grey
import com.example.gharbas.ui.theme.LightGrey
import com.example.gharbas.ui.theme.White
import com.example.gharbas.ui.theme.lightYellow
import com.example.gharbas.view.ui.theme.GharbasTheme
import com.example.gharbas.viewmodel.UserViewModel

class RegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RegisterBody()
        }
    }
}

@Composable
fun  RegisterBody(){

    val userViewModel = remember { UserViewModel(UserRepoImpl()) }

    var email by remember { mutableStateOf("") }
    var password by remember {mutableStateOf("")}
    var first_name by remember {mutableStateOf("")}
    var last_name by remember {mutableStateOf("")}
    var visibility by remember { mutableStateOf(false) }
    var checkbox by remember {mutableStateOf(false)}

    val options = listOf("male", "female")
    var selectedOption by remember {mutableStateOf(options[0])};

    val context = LocalContext.current
    val activity = context as Activity
    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)
    var selectedDate by remember { mutableStateOf("") }

    val sharedPreferences = context.getSharedPreferences(
        "User",
        Context.MODE_PRIVATE
    )

    val editor = sharedPreferences.edit()


    var datepicker = DatePickerDialog(
        context,{
                _,y,m,d-> selectedDate = "$d/${m+1}/$y"

        },year,month,day
    )

    Scaffold { padding ->
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(padding)
        ) {

            Spacer(modifier = Modifier.height(60.dp))
            Text("Sign Up",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                color = lightYellow,
                style = TextStyle(
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier
                    .fillMaxWidth()
            )
            Text(
                "Register to become a member of gharbas community",
                style = TextStyle(
                    textAlign = TextAlign.Center,
                    color = lightYellow
                ),
                modifier = Modifier.padding(20.dp),
            )
            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = first_name,
                onValueChange = { data ->
                    first_name = data
                },
                placeholder = {Text("first name")},
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = White,
                    focusedContainerColor = LightGrey,
                    focusedIndicatorColor = Blue,
                    unfocusedIndicatorColor = androidx.compose.ui.graphics.Color.Transparent
                ),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp),

                )
            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = last_name,
                onValueChange = { data ->
                    last_name = data
                },
                placeholder = {Text("last name")},
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = White,
                    focusedContainerColor = LightGrey,
                    focusedIndicatorColor = Blue,
                    unfocusedIndicatorColor = androidx.compose.ui.graphics.Color.Transparent
                ),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp),

                )

            Spacer(Modifier.height(20.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Gender : ")
                options.forEach { option ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = (option == selectedOption),
                            onClick = {selectedOption = option}
                        )
                        Text(text = option)
                    }
                }
            }


            Spacer(modifier = Modifier.height(30.dp))
            OutlinedTextField(
                value = email,
                onValueChange = {data->
                    email = data
                },
                placeholder = {
                    Text("abc@gmail.com")
                },
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = White,
                    focusedContainerColor = LightGrey,
                    focusedIndicatorColor = Blue,
                    unfocusedIndicatorColor = androidx.compose.ui.graphics.Color.Transparent
                ),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .padding(horizontal = 15.dp)
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { data ->
                    password = data
                },
                placeholder = {Text("*")},
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = White,
                    focusedContainerColor = LightGrey,
                    focusedIndicatorColor = Blue,
                    unfocusedIndicatorColor = androidx.compose.ui.graphics.Color.Transparent
                ),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp),
                visualTransformation = if (visibility) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = {
                        visibility = !visibility
                    }) {
                        Icon(
                            painter = if(visibility)
                                painterResource(com.example.gharbas.R.drawable.baseline_visibility_24)
                            else painterResource(com.example.gharbas.R.drawable.baseline_visibility_off_24),
                            contentDescription = null
                        )
                    }
                }

            )


            Spacer(modifier = Modifier.height(20.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 15.dp)

            ) {
                Checkbox(
                    checked = checkbox,
                    onCheckedChange = {data ->
                        checkbox = data
                    },
                    colors = CheckboxDefaults.colors(

                        checkedColor = Blue,
                        uncheckedColor = Color.Black
                    )
                )
                Text("i agree with terms and condition")
            }
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(
                value = selectedDate,
                onValueChange = { data ->
                    selectedDate = data
                },
                placeholder = {Text("dd/mm/yyyy")},
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = White,
                    focusedContainerColor = LightGrey,
                    disabledIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Blue,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                enabled = false,
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp)
                    .clickable{
                        datepicker.show()
                    }
            )
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = {
                    if(!checkbox) {
                        Toast.makeText(context, "Please accept terms & conditions", Toast.LENGTH_SHORT).show();
                    } else {
                        userViewModel.register(email, password) {
                                success,message,userId->
                            if(success) {
                                val model = UserModel(
                                    id = userId,
                                    firstName = first_name,
                                    lastName = last_name,
                                    email = email,
                                    gender = selectedOption,
                                    dob = selectedDate
                                )
                                userViewModel.addUserToDatabase(userId, model) {
                                        success, message ->
                                    if(success) {
                                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                                        val intent = Intent(
                                            context,
                                            LoginScreen::class.java
                                        )
                                        context.startActivity(intent)
                                        activity.finish()
                                    } else {
                                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                                    }
                                }
                            } else {
                                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                            }

                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp)
                    .height(60.dp)
                ,
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = androidx.compose.ui.graphics.Color.Blue,
                    contentColor = androidx.compose.ui.graphics.Color.White
                )
            ) {

                Text("Sign Up")
            }
            Spacer(modifier = Modifier.height(12.dp))


        }

    }




}


@Composable
@Preview
fun RegisterActivityPreview(){
    RegisterBody()
}
