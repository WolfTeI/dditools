package ar.com.wolftei.ddi.composables.fomularios

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import ar.com.wolftei.ddi.DataClass.DatosFormulario
import ar.com.wolftei.ddi.DataClass.DatosFormularioCheck
import ar.com.wolftei.ddi.Functions.clipBoard
import ar.com.wolftei.ddi.Functions.fechaActual
import ar.com.wolftei.ddi.Functions.horaActual

@Composable
fun FormularioSalida(
    context: Context,
    navController: NavController,
    formularioSalidaViewModel: FormularioSalidaViewModel = hiltViewModel()
) {
    val personal by formularioSalidaViewModel.personal.observeAsState(initial = "")
    val checkPersonal by formularioSalidaViewModel.checkPersonal.observeAsState(initial = false)
    val movil by formularioSalidaViewModel.movil.observeAsState("")
    val jurisdiccion by formularioSalidaViewModel.jurisdiccion.observeAsState("")
    val motivo by formularioSalidaViewModel.motivo.observeAsState("")
    val firma by formularioSalidaViewModel.firma.observeAsState("")

    FormularioSalidaCompose(
        personal = DatosFormulario(
            "Personal",
            {
                formularioSalidaViewModel.onValueChange(
                    it,
                    checkPersonal,
                    movil,
                    jurisdiccion,
                    motivo,
                    firma
                )
            },
            personal
        ),
        checkPersonal = DatosFormularioCheck(
            "con personal a cargo",
            {
                formularioSalidaViewModel.onValueChange(
                    personal,
                    it,
                    movil,
                    jurisdiccion,
                    motivo,
                    firma
                )
            },
            checkPersonal
        ),
        movil = DatosFormulario(
            "Móvil",
            {
                formularioSalidaViewModel.onValueChange(
                    personal,
                    checkPersonal,
                    it,
                    jurisdiccion,
                    motivo,
                    firma
                )
            },
            movil
        ),
        jurisdiccion = DatosFormulario(
            "Jurisdicción",
            {
                formularioSalidaViewModel.onValueChange(
                    personal,
                    checkPersonal,
                    movil,
                    it,
                    motivo,
                    firma
                )
            },
            jurisdiccion
        ),
        motivo = DatosFormulario(
            "Motivo",
            {
                formularioSalidaViewModel.onValueChange(
                    personal,
                    checkPersonal,
                    movil,
                    jurisdiccion,
                    it,
                    firma
                )
            },
            motivo
        ),
        firma = DatosFormulario(
            "Firmado",
            {
                formularioSalidaViewModel.onValueChange(
                    personal,
                    checkPersonal,
                    movil,
                    jurisdiccion,
                    motivo,
                    it
                )
            },
            firma
        ),
        viewModel = formularioSalidaViewModel,
        context = context,
        navController = navController
    )


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormularioSalidaCompose(
    personal: DatosFormulario,
    checkPersonal: DatosFormularioCheck,
    movil: DatosFormulario,
    jurisdiccion: DatosFormulario,
    motivo: DatosFormulario,
    firma: DatosFormulario,
    viewModel: FormularioSalidaViewModel,
    context: Context,
    navController: NavController
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        TextField(
            value = personal.value,
            onValueChange = personal.onChange,
            label = { Text(text = personal.name) },
            singleLine = true,
            maxLines = 1,
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        )
    }
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Checkbox(checked = checkPersonal.value, onCheckedChange = checkPersonal.onChange)
        }
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = checkPersonal.name,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(vertical = 10.dp)
            )
        }
    }
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        TextField(
            value = movil.value,
            onValueChange = movil.onChange,
            label = { Text(text = movil.name) },
            singleLine = true,
            maxLines = 1,
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        )
    }
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        TextField(
            value = jurisdiccion.value,
            onValueChange = jurisdiccion.onChange,
            label = { Text(text = jurisdiccion.name) },
            singleLine = true,
            maxLines = 1,
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        )
    }
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        TextField(
            value = motivo.value,
            onValueChange = motivo.onChange,
            label = { Text(text = motivo.name) },
            singleLine = true,
            maxLines = 1,
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .padding(5.dp)
        )
    }
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        TextField(
            value = firma.value,
            onValueChange = firma.onChange,
            label = { Text(text = firma.name) },
            singleLine = true,
            maxLines = 1,
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        )
    }

    Row {
        ButtonFS(
            viewModel = viewModel,
            context = context,
            navController = navController
        )
    }

}


@Composable
fun ButtonFS(
    viewModel: FormularioSalidaViewModel,
    context: Context,
    navController: NavController
) {
    FilledIconButton(
        onClick = {
            viewModel.salida(context)
            navController.popBackStack()
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 5.dp)
    ) {
        Text(text = "Informar Salida")
    }
}

