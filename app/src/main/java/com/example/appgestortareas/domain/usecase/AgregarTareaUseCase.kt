package com.example.appgestortareas.domain.usecase

import com.example.appgestortareas.domain.model.Tarea
import com.example.appgestortareas.domain.repository.TareaRepository

class AgregarTareaUseCase(
    private val repository: TareaRepository
) {

    suspend operator fun invoke(tarea: Tarea) {

        repository.insertar(tarea)

    }

    }