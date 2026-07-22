package com.example.appgestortareas.di

import android.content.Context
import androidx.room.Room
import com.example.appgestortareas.data.local.db.AppDatabase
import com.example.appgestortareas.data.repository.TareaRepositoryImpl
import com.example.appgestortareas.domain.repository.TareaRepository
import com.example.appgestortareas.domain.usecase.ActualizarTareaUseCase
import com.example.appgestortareas.domain.usecase.AgregarTareaUseCase
import com.example.appgestortareas.domain.usecase.EliminarTareaUseCase
import com.example.appgestortareas.domain.usecase.GetTareaUseCase
import com.example.appgestortareas.domain.usecase.GetTareasUseCase
import com.example.appgestortareas.domain.usecase.TareaUseCase
import com.example.appgestortareas.presentation.viewmodel.TareaViewModel

object AppModule {

    private fun provideDatabase(context: Context): AppDatabase {

        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "tareas_db"
        ).build()

    }

    private fun provideTareaDao(context: Context) =
        provideDatabase(context).tareaDao()

    private fun provideTareaRepository(context: Context): TareaRepository {

        return TareaRepositoryImpl(
            provideTareaDao(context)
        )

    }

    fun provideTareaUseCase(context: Context): TareaUseCase {

        val repository = provideTareaRepository(context)

        return TareaUseCase(
            agregarTarea = AgregarTareaUseCase(repository),

            getTareas = GetTareasUseCase(repository),

            getTarea = GetTareaUseCase(repository),

            actualizarTarea = ActualizarTareaUseCase(repository),

            eliminarTarea = EliminarTareaUseCase(repository)

        )

    }

    fun provideTareaViewModel(context: Context): TareaViewModel {

        return TareaViewModel(
            provideTareaUseCase(context)
        )

    }

}