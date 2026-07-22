package com.example.appgestortareas.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appgestortareas.domain.model.Tarea
import com.example.appgestortareas.domain.usecase.TareaUseCase
import com.example.appgestortareas.presentation.state.TareaUiEvent
import com.example.appgestortareas.presentation.state.TareaUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import android.util.Log
class TareaViewModel(
    private val useCase: TareaUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(TareaUiState())
    val uiState: StateFlow<TareaUiState> = _uiState

    private val _event = MutableSharedFlow<TareaUiEvent>()
    val event = _event
    private var tareas = listOf<Tarea>()

    init {
        cargarTareas()
    }

    fun cargarTareas(){
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
           try{
               delay(2000)
               tareas = useCase.getTareas()
               _uiState.update { it.copy(
                   listaTareas = tareas,
                   isLoading = false
               )

               }
           }catch(e: Exception){
               _uiState.update { it.copy(isLoading = false) }
            _event.emit(TareaUiEvent.MostrarSnackbar(e.message ?: "Error"))

           }

        }
    }
    fun guardarTarea(tarea: Tarea) {

        viewModelScope.launch {

            try {

                useCase.agregarTarea(tarea)
                cargarTareas()


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

    fun obtenerTarea(id: Int) {

        viewModelScope.launch {

            _uiState.update {
                it.copy(isLoading = true)
            }
            Log.d("EDITAR", "Entró a obtenerTarea con id = $id")
            try {

                val tarea = useCase.getTarea(id)
                Log.d("EDITAR", "Tarea encontrada = $tarea")
                _uiState.update {
                    it.copy(
                        tarea = tarea,
                        isLoading = false
                    )
                }

            } catch (e: Exception) {
                Log.d("EDITAR", "Error = ${e.message}")
                _uiState.update {
                    it.copy(isLoading = false)
                }

                _event.emit(
                    TareaUiEvent.MostrarSnackbar(
                        e.message ?: "No se pudo obtener la tarea."
                    )
                )

            }

        }

    }


    fun actualizarTarea(tarea: Tarea) {

        viewModelScope.launch {

            try {

                useCase.actualizarTarea(tarea)

                cargarTareas()

                _event.emit(TareaUiEvent.NavigateBack)

            } catch (e: Exception) {

                _event.emit(
                    TareaUiEvent.MostrarSnackbar(
                        e.message ?: "Ocurrió un error al actualizar la tarea."
                    )
                )

            }

        }

    }

    fun eliminarTarea(tarea: Tarea) {

        viewModelScope.launch {

            try {

                useCase.eliminarTarea(tarea)

                cargarTareas()

            } catch (e: Exception) {

                _event.emit(
                    TareaUiEvent.MostrarSnackbar(
                        e.message ?: "No se pudo eliminar la tarea."
                    )
                )

            }

        }

    }





}