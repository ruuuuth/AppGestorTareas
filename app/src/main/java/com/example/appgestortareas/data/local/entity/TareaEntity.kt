package com.example.appgestortareas.data.local.entity
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tareas")
data class TareaEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val titulo: String,

    val descripcion: String,

    val fecha: String,

    val prioridad: String,

    val completada: Boolean

)