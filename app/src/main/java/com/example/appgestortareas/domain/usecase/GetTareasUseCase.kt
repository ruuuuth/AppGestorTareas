package com.example.appgestortareas.domain.usecase

import com.example.appgestortareas.domain.model.Tarea
import com.example.appgestortareas.domain.repository.TareaRepository

class GetTareasUseCase(private val repo: TareaRepository) {
    suspend operator fun invoke() : List<Tarea>{
        return repo.getAll()
    }
}