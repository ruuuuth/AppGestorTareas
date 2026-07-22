package com.example.appgestortareas.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.appgestortareas.data.local.entity.TareaEntity
import com.example.appgestortareas.domain.model.Tarea

@Dao
interface TareaDAO {

    @Insert
    suspend fun agregar(tarea: TareaEntity)
    @Query("SELECT * FROM tareas")
    suspend fun getAll(): List<TareaEntity>
    @Query("SELECT * FROM tareas WHERE id= :id")
    suspend fun getTareaById(id: Int): TareaEntity
    @Update
    suspend fun actualizar(tarea: TareaEntity)

    @Delete
    suspend fun eliminar(tarea: TareaEntity)
}