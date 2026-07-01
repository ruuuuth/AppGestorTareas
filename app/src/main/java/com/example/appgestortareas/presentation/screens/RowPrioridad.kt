package com.example.appgestortareas.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun RowPrioridad(

    texto: String,

    seleccionado: Boolean,

    onClick: () -> Unit

) {

    Row(

        modifier = Modifier
            .wrapContentWidth()
            .clickable {
                onClick()
            },

        verticalAlignment = Alignment.CenterVertically

    ) {

        RadioButton(

            selected = seleccionado,

            onClick = onClick

        )

        Text(text = texto)

    }

}