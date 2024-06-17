package com.example.envelops

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.envelops.navigation.NavigationBar
import com.example.envelops.ui.theme.EnvelopsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EnvelopsTheme {
                NavigationBar()
            }
        }
    }
}

