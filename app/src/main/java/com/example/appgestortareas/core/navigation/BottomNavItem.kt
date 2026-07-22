package com.example.appgestortareas.core.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    val ruta: String,
    val titulo: String,
    val icono: ImageVector

) {
    data object Inicio : BottomNavItem(
        ruta = NavRutas.INICIO,
        titulo = "Inicio",
        icono = Icons.Default.Home
    )

    data object Agregar : BottomNavItem(
        ruta = NavRutas.AGREGAR,
        titulo = "Agregar",
        icono = Icons.Default.Add
    )

    data object Completadas : BottomNavItem(
        ruta = NavRutas.COMPLETADAS,
        titulo = "Completadas",
        icono = Icons.Default.CheckCircle
    )
}

