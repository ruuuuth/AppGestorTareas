package com.example.appgestortareas.data.repository

import com.example.appgestortareas.data.local.dao.TareaDAO
import com.example.appgestortareas.data.mapper.TareaMapper
import com.example.appgestortareas.domain.model.Tarea
import com.example.appgestortareas.domain.repository.TareaRepository

class TareaRepositoryImpl(
    private val dao: TareaDAO
) : TareaRepository {

    override suspend fun insertar(tarea: Tarea) {

        dao.agregar(
            TareaMapper.toEntity(tarea)
        )

    }

}