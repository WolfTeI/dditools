package ar.com.wolftei.ddi.composables.fomularios

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ar.com.wolftei.ddi.DataClass.DatosFormulario
import ar.com.wolftei.ddi.Functions.clipBoard
import ar.com.wolftei.ddi.Functions.fechaActual

@Composable
fun FormularioCcarHecho(context: Context, navController: NavController) {
    var ipp by remember { mutableStateOf("") }
    var caratula by remember { mutableStateOf("") }
    var jurisdiccion by remember { mutableStateOf("") }
    var denunciante by remember { mutableStateOf("") }
    var intervencion by remember { mutableStateOf("") }
    var contenido by remember { mutableStateOf("") }
    var firma by remember { mutableStateOf("") }
    RowInputExtracto(
        ipp = DatosFormulario("IPP", { ipp = it }, ipp),
        caratula = DatosFormulario("Caratula", { caratula = it }, caratula),
        jurisdiccion = DatosFormulario("Jurisdicción", { jurisdiccion = it }, jurisdiccion),
        denunciante = DatosFormulario("Denunciante", { denunciante = it }, denunciante),
        intervencion = DatosFormulario("Intervención", { intervencion = it }, intervencion),
        contenido = DatosFormulario("Extracto", { contenido = it }, contenido),
        firma = DatosFormulario("Firmado", { firma = it }, firma),
        context = context,
        navController = navController
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RowInputExtracto(
    ipp: DatosFormulario,
    caratula: DatosFormulario,
    jurisdiccion: DatosFormulario,
    denunciante: DatosFormulario,
    intervencion: DatosFormulario,
    contenido: DatosFormulario,
    firma: DatosFormulario,
    context: Context,
    navController: NavController
) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(top = 20.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Column {
            TextField(
                value = ipp.value,
                onValueChange = ipp.onChange,
                label = { Text(text = ipp.name) },
                singleLine = true,
                maxLines = 1,
                modifier = Modifier
                    .width(200.dp)
                    .padding(bottom = 3.dp, start = 5.dp, end = 2.dp)
            )
        }

        Column {
            TextField(
                value = caratula.value,
                onValueChange = caratula.onChange,
                label = { Text(text = caratula.name) },
                singleLine = true,
                maxLines = 1,
                modifier = Modifier
                    .width(200.dp)
                    .padding(bottom = 3.dp, start = 2.dp, end = 5.dp)
            )
        }
    }
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        TextField(
            value = denunciante.value,
            onValueChange = denunciante.onChange,
            label = { Text(text = denunciante.name) },
            singleLine = true,
            maxLines = 1,
            modifier = Modifier
                .width(400.dp)
                .padding(top = 3.dp, bottom = 3.dp, start = 5.dp, end = 2.dp)
        )

    }
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Column {
            TextField(
                value = jurisdiccion.value,
                onValueChange = jurisdiccion.onChange,
                label = { Text(text = jurisdiccion.name) },
                singleLine = true,
                maxLines = 1,
                modifier = Modifier
                    .width(200.dp)
                    .padding(top = 3.dp, bottom = 3.dp, start = 5.dp, end = 2.dp)
            )
        }
        Column {
            TextField(
                value = intervencion.value,
                onValueChange = intervencion.onChange,
                label = { Text(text = intervencion.name) },
                singleLine = true,
                maxLines = 1,
                modifier = Modifier
                    .width(200.dp)
                    .padding(top = 3.dp, bottom = 3.dp, start = 2.dp, end = 5.dp)
            )
        }

    }
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        TextField(
            value = contenido.value,
            onValueChange = contenido.onChange,
            label = { Text(text = contenido.name) },
            singleLine = false,
            maxLines = 5,
            modifier = Modifier
                .width(400.dp)
                .height(150.dp)
                .padding(vertical = 3.dp, horizontal = 5.dp)
        )
    }
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        TextField(
            value = firma.value,
            onValueChange = firma.onChange,
            label = { Text(text = firma.name) },
            singleLine = true,
            maxLines = 1,
            modifier = Modifier
                .width(400.dp)
                .padding(vertical = 3.dp, horizontal = 5.dp)
        )
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        FilledIconButton(
            onClick = {
                crearExtractoHecho(
                    ipp,
                    caratula,
                    jurisdiccion,
                    denunciante,
                    intervencion,
                    contenido,
                    firma,
                    context
                )
                navController.popBackStack()
            },
            modifier = Modifier
                .width(400.dp)
                .padding(horizontal = 5.dp)
        ) {
            Text(text = "Nuevo Extracto")
        }
    }


}

private fun crearExtractoHecho(
    ipp: DatosFormulario,
    caratula: DatosFormulario,
    jurisdiccion: DatosFormulario,
    denunciante: DatosFormulario,
    intervencion: DatosFormulario,
    contenido: DatosFormulario,
    firma: DatosFormulario,
    context: Context
) {
    val fecha = "*${fechaActual()}*"
    val encabezado =
        "\n\n*DDI MORON CCA. ${caratula.value.trim()} ${
            ipp.name.uppercase().trim()
        } ${ipp.value.trim()} - ${jurisdiccion.value.trim()}*"
    var denuncianteVar = ""
    if (denunciante.value != "") {
        denuncianteVar = "\n\n*${denunciante.name.uppercase().trim()}:* ${denunciante.value.trim()}"
    }
    var intervencionVar = ""
    if (intervencion.value != "") {
        intervencionVar =
            "\n\n*${intervencion.name.uppercase().trim()}:* ${intervencion.value.trim()}"
    }
    val genesis = "\n\n*GÉNESIS:* ${contenido.value.trim()}"
    val firma = "\n\n*FDO. ${firma.value.uppercase().trim()}.*"

    val extracto =
        fecha + encabezado + denuncianteVar + intervencionVar + genesis + firma
    clipBoard(context, extracto)
}

