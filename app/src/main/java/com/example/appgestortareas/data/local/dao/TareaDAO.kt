package com.example.appgestortareas.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import com.example.appgestortareas.data.local.entity.TareaEntity

@Dao
interface TareaDAO {
    // Inserta una nueva tarea en la base de datos de forma asíncrona
    @Insert
    suspend fun agregar(tarea: TareaEntity)

}