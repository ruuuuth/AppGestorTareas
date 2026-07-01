package com.example.appgestortareas.domain.model

data class Tarea (
    val id: Int = 0,
    val titulo: String,
    val descripcion: String,
    val fecha: String,
    val prioridad: String,
    val completada: Boolean = false
)