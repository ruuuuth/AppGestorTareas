package com.example.appgestortareas.domain.repository

import com.example.appgestortareas.domain.model.Tarea

interface TareaRepository {

    suspend fun insertar(tarea: Tarea)

    suspend fun getAll(): List<Tarea>

    suspend fun getTareabyId(id: Int): Tarea
    suspend fun actualizar(tarea: Tarea)
    suspend fun eliminar(tarea: Tarea)

}