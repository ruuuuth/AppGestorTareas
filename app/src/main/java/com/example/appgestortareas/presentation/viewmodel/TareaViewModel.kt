package com.example.appgestortareas.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appgestortareas.domain.model.Tarea
import com.example.appgestortareas.domain.usecase.TareaUseCase
import com.example.appgestortareas.presentation.state.TareaUiEvent
import com.example.appgestortareas.presentation.state.TareaUiState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TareaViewModel(
    private val useCase: TareaUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(TareaUiState())
    val uiState: StateFlow<TareaUiState> = _uiState

    private val _event = MutableSharedFlow<TareaUiEvent>()
    val event = _event

    fun guardarTarea(tarea: Tarea) {

        viewModelScope.launch {

            try {

                useCase.agregarTarea(tarea)

                _event.emit(TareaUiEvent.NavigateBack)

            } catch (e: Exception) {

                _event.emit(
                    TareaUiEvent.MostrarSnackbar(
                        e.message ?: "Ocurrió un error al guardar la tarea."
                    )
                )

            }

        }

    }

}