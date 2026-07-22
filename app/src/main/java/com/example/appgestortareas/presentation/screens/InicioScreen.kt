package com.example.appgestortareas.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.appgestortareas.core.navigation.NavRutas
import com.example.appgestortareas.presentation.components.CargandoScreen
import com.example.appgestortareas.presentation.viewmodel.TareaViewModel
import android.util.Log
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.TextButton
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.appgestortareas.domain.model.Tarea

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InicioScreen(vm: TareaViewModel, navController: NavController) {
    val uiState by vm.uiState.collectAsState()
    var tareaAEliminar by remember {
        mutableStateOf<Tarea?>(null)
    }

    Scaffold(
        topBar = {

            CenterAlignedTopAppBar(

                title = {

                    Text(
                        text = "Gestión de Tareas",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )

                },

                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF3F51B5)
                )

            )

        }
    )
    {paddingValues ->
        Box(modifier = Modifier.padding(paddingValues).fillMaxSize()){
            Column(modifier = Modifier.fillMaxSize()) {
            if(uiState.isLoading){
                CargandoScreen()
            }else{
                LazyColumn(
                    modifier = Modifier.fillMaxSize().padding(horizontal=12.dp)
                    , verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(uiState.listaTareas.filter { !it.completada }){tarea->
                        Card(modifier = Modifier.fillMaxWidth()) {
                            Column(modifier = Modifier.padding(16.dp)) {
                               Text(
                                   "Título: ${tarea.titulo}",
                                   style= MaterialTheme.typography.titleMedium,
                                   fontWeight = FontWeight.Bold
                               )
                                Spacer(modifier = Modifier.height(2.dp))
                                Text("${tarea.descripcion}")
                                Spacer(modifier = Modifier.height(6.dp))
                                Text("Prioridad:  ${tarea.prioridad}")
                                Spacer(modifier = Modifier.height(6.dp))
                                Text("Fecha: ${tarea.fecha}")

                                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                                    Button(

                                        onClick = {
                                            Log.d("EDITAR", "Voy a editar la tarea ${tarea.id}")
                                            navController.navigate("${NavRutas.EDITAR}/${tarea.id}")}
                                    ) {
                                        Icon(Icons.Default.Edit,"")
                                        Spacer(modifier = Modifier.width(4.dp))
                                        Text("Editar")
                                    }
                                    OutlinedButton(
                                        onClick = {
                                            tareaAEliminar = tarea
                                           // vm.eliminarTarea(tarea)
                                        }
                                    ) {
                                        Icon(Icons.Default.Delete,"")
                                        Spacer(modifier = Modifier.width(4.dp))
                                        Text("Eliminar")
                                    }

                                    OutlinedIconButton(
                                         onClick = {
                                             val tareaCompletada = tarea.copy(
                                                 completada = true
                                             )

                                             vm.actualizarTarea(tareaCompletada)
                                         }
                                     ) {
                                         Icon(
                                             imageVector = Icons.Default.Check,
                                             contentDescription = "Completar"
                                         )
                                     }


                                }
                            }
                        }

                    }
                }
            }
            }
        }
    }


    if (tareaAEliminar != null) {

        AlertDialog(

             onDismissRequest = {
                 tareaAEliminar = null
             },

             title = {
                 Text("Eliminar tarea")
             },

             text = {
                 Text("¿Estás seguro de que deseas eliminar esta tarea?")
             },

             confirmButton = {

                 TextButton(

                     onClick = {

                         vm.eliminarTarea(tareaAEliminar!!)

                         tareaAEliminar = null

                     }

                 ) {

                     Text("Eliminar")

                 }

             },

             dismissButton = {

                 TextButton(

                     onClick = {

                         tareaAEliminar = null

                     }

                 ) {

                     Text("Cancelar")

                 }

             }

         )

    }
}