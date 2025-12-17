package com.example.gharbas.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.gharbas.R
import com.example.gharbas.ui.theme.White
import com.example.gharbas.ui.theme.lightYellow
import com.example.gharbas.view.ui.theme.GharbasTheme

class DashboardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DashboardBody()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardBody(){
    Scaffold (
        topBar ={
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    navigationIconContentColor = White,
                    actionIconContentColor =White,
                    titleContentColor =White,
                    containerColor = lightYellow
                ),
                title = {
                    Text("Dashboard")
                },
                navigationIcon ={
                    IconButton(onClick = {}) {
                        Icon(
                            painter = painterResource(R.drawable.outline_notifications_24),
                            contentDescription = null
                        )
                    }
                    IconButton(onClick = {}) {
                        Icon(
                            painter = painterResource(R.drawable.baseline_settings_24),
                            contentDescription = null
                        )
                    }
                    IconButton(onClick = {}) {
                        Icon(
                            painter = painterResource(R.drawable.baseline_dehaze_24),
                            contentDescription = null
                        )
                    }




                }

            )
        }

    )
    { padding ->
         Column(
             modifier = Modifier.fillMaxSize().padding(padding)
         ) {


         }
     }
}

@Preview
@Composable
fun DashboardPreview(){
    DashboardBody()
}