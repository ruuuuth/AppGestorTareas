package com.example.appgestortareas.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import com.example.appgestortareas.data.local.entity.TareaEntity

@Dao
interface TareaDAO {

    @Insert
    suspend fun agregar(tarea: TareaEntity)

}