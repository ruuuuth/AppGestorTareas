package com.example.appgestortareas.presentation.state

import com.example.appgestortareas.domain.model.Tarea

data class TareaUiState(

    val listaTareas: List<Tarea> = emptyList(),

    val tarea: Tarea? = null,

    val isLoading: Boolean = false,

    val search: String = "",

    val tareaDelete: Boolean = false




)