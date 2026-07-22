package com.example.appgestortareas.domain.usecase

data class TareaUseCase(

    val agregarTarea: AgregarTareaUseCase,
    val getTareas : GetTareasUseCase,
    val getTarea: GetTareaUseCase,
    val actualizarTarea: ActualizarTareaUseCase,
    val eliminarTarea: EliminarTareaUseCase
)