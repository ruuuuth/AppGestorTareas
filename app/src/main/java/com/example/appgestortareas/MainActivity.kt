package com.example.appgestortareas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.appgestortareas.core.navigation.AppNavigation
import com.example.appgestortareas.core.ui.theme.AppGestorTareasTheme
import com.example.appgestortareas.di.AppModule

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val viewModel =
            AppModule.provideTareaViewModel(applicationContext)
        setContent {
            AppGestorTareasTheme {

                AppNavigation(viewModel)

            }
        }
    }
}

