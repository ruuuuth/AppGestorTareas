package com.example.appgestortareas.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.appgestortareas.domain.model.Tarea
import com.example.appgestortareas.presentation.state.TareaUiEvent
import com.example.appgestortareas.presentation.viewmodel.TareaViewModel
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.rememberDatePickerState
import androidx.navigation.NavController
import com.example.appgestortareas.core.navigation.NavRutas
import android.util.Log
import com.example.appgestortareas.presentation.components.RowPrioridad


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgregarScreen(vm: TareaViewModel, navController: NavController,id: Int=0) {

    var titulo by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var fecha by remember { mutableStateOf("") }
    var prioridad by remember { mutableStateOf("Alta") }
    var mostrarCalendario by remember {
        mutableStateOf(false)
    }

    val datePickerState = rememberDatePickerState()

    var tituloError by remember { mutableStateOf(false) }
    var descripcionError by remember { mutableStateOf(false) }
    var fechaError by remember { mutableStateOf(false) }

    val uiState by vm.uiState.collectAsState()

/////
    LaunchedEffect(id) {
        Log.d("EDITAR", "AgregarScreen recibió id = $id")
        if (id != 0) {

            vm.obtenerTarea(id)

        }

    }
    LaunchedEffect(uiState.tarea) {

        uiState.tarea?.let { tarea ->

            titulo = tarea.titulo
            descripcion = tarea.descripcion
            fecha = tarea.fecha
            prioridad = tarea.prioridad

        }

    }


    ///
    val snackbarHostState = remember {
        SnackbarHostState()
    }

    LaunchedEffect(Unit) {

        vm.event.collect { event ->

            when (event) {

                is TareaUiEvent.MostrarSnackbar -> {

                    snackbarHostState.showSnackbar(event.mensaje)

                }

                TareaUiEvent.NavigateBack -> {
                    navController.navigate(NavRutas.INICIO)
                    snackbarHostState.showSnackbar("Tarea guardada correctamente")

                    titulo = ""
                    descripcion = ""
                    fecha = ""
                    prioridad = "Alta"


                }

            }

        }

    }

    Scaffold(

        snackbarHost = {
            SnackbarHost(snackbarHostState)
        },

        topBar = {

            CenterAlignedTopAppBar(

                title = {

                    Text(
                        text = "Nueva Tarea",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )

                },

                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF3F51B5)
                )

            )

        }

    ) { paddingValues ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {

            Column(

                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 12.dp, vertical = 6.dp),

                verticalArrangement = Arrangement.spacedBy(4.dp)

            ) {

                OutlinedTextField(

                    value = titulo,

                    onValueChange = {

                        titulo = it
                        tituloError = false

                    },

                    isError = tituloError,

                    supportingText = {

                        if (tituloError) {

                            Text("Ingrese un título")

                        }

                    },

                    label = {
                        Text("Título")
                    },

                    modifier = Modifier.fillMaxWidth()

                )

                OutlinedTextField(

                    value = descripcion,

                    onValueChange = {

                        descripcion = it
                        descripcionError = false

                    },

                    isError = descripcionError,

                    supportingText = {

                        if (descripcionError) {

                            Text("Ingrese una descripción")

                        }

                    },

                    label = {
                        Text("Descripción")
                    },

                    minLines = 2,

                    maxLines = 3,

                    modifier = Modifier.fillMaxWidth()

                )

                OutlinedTextField(

                    value = fecha,

                    onValueChange = {},

                    readOnly = true,

                    isError = fechaError,

                    supportingText = {

                        if (fechaError) {

                            Text("Seleccione una fecha")

                        }

                    },

                    label = {

                        Text("Fecha")

                    },

                    trailingIcon = {

                        IconButton(

                            onClick = {

                                mostrarCalendario = true

                            }

                        ) {

                            Icon(

                                imageVector = Icons.Default.DateRange,

                                contentDescription = "Calendario"

                            )

                        }

                    },

                    modifier = Modifier.fillMaxWidth()

                )


                Text(
                    text = "Prioridad",
                    fontWeight = FontWeight.Bold
                )

                Row(

                    modifier = Modifier.fillMaxWidth(),

                    horizontalArrangement = Arrangement.SpaceEvenly

                ) {

                    RowPrioridad(
                        texto = "Alta",
                        seleccionado = prioridad == "Alta"
                    ) {
                        prioridad = "Alta"
                    }

                    RowPrioridad(
                        texto = "Media",
                        seleccionado = prioridad == "Media"
                    ) {
                        prioridad = "Media"
                    }

                    RowPrioridad(
                        texto = "Baja",
                        seleccionado = prioridad == "Baja"
                    ) {
                        prioridad = "Baja"
                    }

                }

                Button(

                    onClick = {

                        if (titulo.isBlank()) {

                            tituloError = true
                            return@Button

                        }

                        if (descripcion.isBlank()) {

                            descripcionError = true
                            return@Button

                        }

                        if (fecha.isBlank()) {

                            fechaError = true
                            return@Button

                        }

                        val tarea = Tarea(

                            id = id,

                            titulo = titulo,

                            descripcion = descripcion,

                            fecha = fecha,

                            prioridad = prioridad,

                            completada = false

                        )

                        if (id==0){
                            vm.guardarTarea(tarea)
                        }else{

                            vm.actualizarTarea(tarea)
                        }



                    },

                    modifier = Modifier.fillMaxWidth()

                ) {

                    Text("Guardar Tarea")

                }

            }

        }

    }



    if (mostrarCalendario) {

        DatePickerDialog(

            onDismissRequest = {

                mostrarCalendario = false

            },

            confirmButton = {

                Button(

                    onClick = {

                        val millis = datePickerState.selectedDateMillis

                        if (millis != null) {

                            val formatter = java.text.SimpleDateFormat(
                                "dd/MM/yyyy",
                                java.util.Locale.getDefault()
                            )
                            formatter.timeZone = java.util.TimeZone.getTimeZone("UTC")
                            fecha = formatter.format(java.util.Date(millis))

                            fechaError = false

                        }

                        mostrarCalendario = false

                    }

                ) {

                    Text("Aceptar")

                }

            },

            dismissButton = {

                Button(

                    onClick = {

                        mostrarCalendario = false

                    }

                ) {

                    Text("Cancelar")

                }

            }

        ) {

            DatePicker(

                state = datePickerState

            )

        }

    }

}