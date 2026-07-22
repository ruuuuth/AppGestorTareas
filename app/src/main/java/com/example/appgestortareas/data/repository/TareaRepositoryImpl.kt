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

    override suspend fun getAll(): List<Tarea> =
       dao.getAll().map { TareaMapper.toDomain(it) }

    override suspend fun getTareabyId(id: Int): Tarea =
        dao.getTareaById(id).let { TareaMapper.toDomain(it) }

    override suspend fun actualizar(tarea: Tarea) {
       dao.actualizar(TareaMapper.toEntity(tarea))
    }

    override suspend fun eliminar(tarea: Tarea) {
        dao.eliminar(TareaMapper.toEntity(tarea))
    }


}