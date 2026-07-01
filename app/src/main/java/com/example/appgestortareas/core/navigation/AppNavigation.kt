package com.example.appgestortareas.core.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.appgestortareas.presentation.screens.AgregarScreen
import com.example.appgestortareas.presentation.screens.InicioScreen
import com.example.appgestortareas.presentation.screens.ListaScreen
import com.example.appgestortareas.presentation.viewmodel.TareaViewModel

@Composable
fun AppNavigation(vm: TareaViewModel) {

    val navController = rememberNavController()

    val opciones = listOf(
        BottomNavItem.Inicio,
        BottomNavItem.Agregar,
        BottomNavItem.Lista
    )

    Scaffold(

        bottomBar = {

            NavigationBar {

                val rutaActual = navController.currentBackStackEntryAsState()
                    .value?.destination?.route

                opciones.forEach { item ->

                    NavigationBarItem(

                        selected = rutaActual == item.ruta,

                        onClick = {
                            navController.navigate(item.ruta)
                        },

                        icon = {
                            Icon(item.icono, null)
                        },

                        label = {
                            Text(item.titulo)
                        }

                    )

                }

            }

        }

    ) { paddingValues ->

        NavHost(

            navController = navController,

            startDestination = NavRutas.INICIO,

            modifier = Modifier.padding(paddingValues)

        ) {

            composable(NavRutas.INICIO) {
                InicioScreen()
            }

            composable(NavRutas.AGREGAR) {
                AgregarScreen(vm)
            }

            composable(NavRutas.LISTA) {
                ListaScreen()
            }

        }

    }

}