package com.example.appgestortareas.domain.repository

import com.example.appgestortareas.domain.model.Tarea

interface TareaRepository {

    suspend fun insertar(tarea: Tarea)

}