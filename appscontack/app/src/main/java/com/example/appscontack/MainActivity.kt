package com.example.appscontack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.appscontack.ui.navigation.AppNavigation
import com.example.appscontack.ui.theme.AppsContackTheme // Pastikan nama tema ini sesuai

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Gunakan tema aplikasi Anda (biasanya dibuat otomatis)
            AppsContackTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Memulai navigasi aplikasi
                    AppNavigation()
                }
            }
        }
    }
}