package com.example.appgestortareas.domain.usecase

import com.example.appgestortareas.domain.model.Tarea
import com.example.appgestortareas.domain.repository.TareaRepository

class ActualizarTareaUseCase(private val repo: TareaRepository) {

    suspend operator fun invoke(tarea: Tarea) {
        // validaciones
        repo.actualizar(tarea)
    }

}