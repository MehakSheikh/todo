package com.example.todo

import android.content.Intent
import android.os.Bundle
import android.window.SplashScreen
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContent{
            SplashScreen {
                // Navigate to MainActivity after the splash screen
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}

@Composable
fun SplashScreen(onTimeout: () -> Unit) {
    // Launch an effect to delay for 2 seconds and then call onTimeout
    LaunchedEffect(Unit) {
        delay(2000) // Delay for 2 seconds
        onTimeout()
    }

    // UI Content of the Splash Screen
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // App Logo
            Image(
                painter = painterResource(id = R.drawable.ic_app_logo), // Replace with your logo
                contentDescription = "App Logo",
                modifier = Modifier.size(150.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            // App Name
            Text(
                text = "To-Do App",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Preview
@Composable
fun SplashScreenPreview() {
    SplashScreen(onTimeout = {})
}