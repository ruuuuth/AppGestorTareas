package com.example.appgestortareas.presentation.state

sealed class TareaUiEvent {

    data class MostrarSnackbar(
        val mensaje: String
    ) : TareaUiEvent()

    object NavigateBack : TareaUiEvent()

}