package com.example.appgestortareas.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.appgestortareas.data.local.dao.TareaDAO
import com.example.appgestortareas.data.local.entity.TareaEntity

@Database(
    entities = [TareaEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun tareaDao(): TareaDAO

}