package com.example.appgestortareas.data.mapper

import com.example.appgestortareas.data.local.entity.TareaEntity
import com.example.appgestortareas.domain.model.Tarea

object TareaMapper {

    fun toDomain(entidad: TareaEntity): Tarea =
        Tarea(
            id = entidad.id,
            titulo = entidad.titulo,
            descripcion = entidad.descripcion,
            fecha = entidad.fecha,
            prioridad = entidad.prioridad,
            completada = entidad.completada
        )

    fun toEntity(tarea: Tarea): TareaEntity =
        TareaEntity(
            id = tarea.id,
            titulo = tarea.titulo,
            descripcion = tarea.descripcion,
            fecha = tarea.fecha,
            prioridad = tarea.prioridad,
            completada = tarea.completada
        )

}