package com.example.appgestortareas.domain.usecase

import com.example.appgestortareas.domain.model.Tarea
import com.example.appgestortareas.domain.repository.TareaRepository

class GetTareaUseCase(private val repo: TareaRepository) {
    suspend operator fun invoke(id: Int): Tarea{
        if(id<=0) throw Exception("ID inválido")
        return repo.getTareabyId(id) ?: throw Exception("Tarea no existe")

    }
}