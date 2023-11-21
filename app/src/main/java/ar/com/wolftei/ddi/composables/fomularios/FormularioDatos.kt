package ar.com.wolftei.ddi.composables.fomularios

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import ar.com.wolftei.ddi.DataClass.DatosFormulario
import ar.com.wolftei.ddi.DataClass.DatosFormularioCheck
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun FormularoioDatos(
    context: Context,
    navController: NavController,
    modifier: Modifier,
    formularioDatosViewModel: FormularioDatosViewModel = hiltViewModel()
) {
    val scope = rememberCoroutineScope()
    val nombre by formularioDatosViewModel.nombre.observeAsState(initial = "")
    val nacionalidad by formularioDatosViewModel.nacionalidad.observeAsState(initial = "")
    val edad by formularioDatosViewModel.edad.observeAsState(initial = "")
    val estadoCivil by formularioDatosViewModel.estadoCivil.observeAsState(initial = "")
    val sabeLeerYEscribir by formularioDatosViewModel.leeYEscribe.observeAsState(initial = true)
    val ocupacion by formularioDatosViewModel.profesion.observeAsState(initial = "")
    val domicilio by formularioDatosViewModel.domicilio.observeAsState(initial = "")
    val dni by formularioDatosViewModel.dni.observeAsState(initial = "")
    val fechaNacimiento by formularioDatosViewModel.fechaNacimiento.observeAsState(initial = "")
    val lugarNacimiento by formularioDatosViewModel.lugarNacimiento.observeAsState(initial = "")
    val padre by formularioDatosViewModel.nombrePadre.observeAsState(initial = "")
    val padreVive by formularioDatosViewModel.padreVive.observeAsState(initial = false)
    val madre by formularioDatosViewModel.nombreMadre.observeAsState(initial = "")
    val madreVive by formularioDatosViewModel.madreVive.observeAsState(initial = false)

    FormularioDatosCompose(
        nombre = DatosFormulario("Nombre", {
            formularioDatosViewModel.onChangeValue(
                it,
                nacionalidad,
                edad,
                estadoCivil,
                ocupacion,
                sabeLeerYEscribir,
                domicilio,
                dni,
                fechaNacimiento,
                lugarNacimiento,
                padre,
                padreVive,
                madre,
                madreVive
            )
        }, nombre),
        nacionalidad = DatosFormulario("Nacionalidad", {
            formularioDatosViewModel.onChangeValue(
                nombre,
                it,
                edad,
                estadoCivil,
                ocupacion,
                sabeLeerYEscribir,
                domicilio,
                dni,
                fechaNacimiento,
                lugarNacimiento,
                padre,
                padreVive,
                madre,
                madreVive
            )
        }, nacionalidad),
        edad = DatosFormulario("Edad", {
            formularioDatosViewModel.onChangeValue(
                nombre,
                nacionalidad,
                it,
                estadoCivil,
                ocupacion,
                sabeLeerYEscribir,
                domicilio,
                dni,
                fechaNacimiento,
                lugarNacimiento,
                padre,
                padreVive,
                madre,
                madreVive
            )
        }, edad),
        estadoCivil = DatosFormulario("Estado Civil", {
            formularioDatosViewModel.onChangeValue(
                nombre,
                nacionalidad,
                edad,
                it,
                ocupacion,
                sabeLeerYEscribir,
                domicilio,
                dni,
                fechaNacimiento,
                lugarNacimiento,
                padre,
                padreVive,
                madre,
                madreVive
            )
        }, estadoCivil),
        sabeLeerYEscribir = DatosFormularioCheck(
            "Lee y escribe",
            {
                formularioDatosViewModel.onChangeValue(
                    nombre,
                    nacionalidad,
                    edad,
                    estadoCivil,
                    ocupacion,
                    it,
                    domicilio,
                    dni,
                    fechaNacimiento,
                    lugarNacimiento,
                    padre,
                    padreVive,
                    madre,
                    madreVive
                )
            },
            sabeLeerYEscribir
        ),
        ocupacion = DatosFormulario("Ocupación", {
            formularioDatosViewModel.onChangeValue(
                nombre,
                nacionalidad,
                edad,
                estadoCivil,
                it,
                sabeLeerYEscribir,
                domicilio,
                dni,
                fechaNacimiento,
                lugarNacimiento,
                padre,
                padreVive,
                madre,
                madreVive
            )
        }, ocupacion),
        domicilio = DatosFormulario("Domicilio", {
            formularioDatosViewModel.onChangeValue(
                nombre,
                nacionalidad,
                edad,
                estadoCivil,
                ocupacion,
                sabeLeerYEscribir,
                it,
                dni,
                fechaNacimiento,
                lugarNacimiento,
                padre,
                padreVive,
                madre,
                madreVive
            )
        }, domicilio),
        dni = DatosFormulario("DNI", {
            formularioDatosViewModel.onChangeValue(
                nombre,
                nacionalidad,
                edad,
                estadoCivil,
                ocupacion,
                sabeLeerYEscribir,
                domicilio,
                it,
                fechaNacimiento,
                lugarNacimiento,
                padre,
                padreVive,
                madre,
                madreVive
            )
        }, dni),
        fechaNacimiento = DatosFormulario(
            "Fecha de Nacimiento",
            {
                formularioDatosViewModel.onChangeFechaNacimiento(it)
            },
            fechaNacimiento
        ),
        lugarNacimiento = DatosFormulario(
            "Lugar de Nac.",
            {
                formularioDatosViewModel.onChangeValue(
                    nombre,
                    nacionalidad,
                    edad,
                    estadoCivil,
                    ocupacion,
                    sabeLeerYEscribir,
                    domicilio,
                    dni,
                    fechaNacimiento,
                    it,
                    padre,
                    padreVive,
                    madre,
                    madreVive
                )
            },
            lugarNacimiento
        ),
        padre = DatosFormulario("Padre", {
            formularioDatosViewModel.onChangeValue(
                nombre,
                nacionalidad,
                edad,
                estadoCivil,
                ocupacion,
                sabeLeerYEscribir,
                domicilio,
                dni,
                fechaNacimiento,
                lugarNacimiento,
                it,
                padreVive,
                madre,
                madreVive
            )
        }, padre),
        padreVive = DatosFormularioCheck("Vive", {
            formularioDatosViewModel.onChangeValue(
                nombre,
                nacionalidad,
                edad,
                estadoCivil,
                ocupacion,
                sabeLeerYEscribir,
                domicilio,
                dni,
                fechaNacimiento,
                lugarNacimiento,
                padre,
                it,
                madre,
                madreVive
            )
        }, padreVive),
        madre = DatosFormulario("Madre", {
            formularioDatosViewModel.onChangeValue(
                nombre,
                nacionalidad,
                edad,
                estadoCivil,
                ocupacion,
                sabeLeerYEscribir,
                domicilio,
                dni,
                fechaNacimiento,
                lugarNacimiento,
                padre,
                padreVive,
                it,
                madreVive
            )
        }, madre),
        madreVive = DatosFormularioCheck("Vive", {
            formularioDatosViewModel.onChangeValue(
                nombre,
                nacionalidad,
                edad,
                estadoCivil,
                ocupacion,
                sabeLeerYEscribir,
                domicilio,
                dni,
                fechaNacimiento,
                lugarNacimiento,
                padre,
                padreVive,
                madre,
                it
            )
        }, madreVive),
        context = context,
        modifier = modifier,
        navController = navController,
        viewModel = formularioDatosViewModel,
        scope = scope
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormularioDatosCompose(
    nombre: DatosFormulario,
    nacionalidad: DatosFormulario,
    edad: DatosFormulario,
    estadoCivil: DatosFormulario,
    sabeLeerYEscribir: DatosFormularioCheck,
    ocupacion: DatosFormulario,
    domicilio: DatosFormulario,
    dni: DatosFormulario,
    fechaNacimiento: DatosFormulario,
    lugarNacimiento: DatosFormulario,
    padre: DatosFormulario,
    padreVive: DatosFormularioCheck,
    madre: DatosFormulario,
    madreVive: DatosFormularioCheck,
    context: Context,
    modifier: Modifier,
    navController: NavController,
    viewModel: FormularioDatosViewModel,
    scope: CoroutineScope
) {
    //        Campo de Nombre
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 10.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        TextField(
            value = nombre.value,
            onValueChange = nombre.onChange,
            label = { Text(text = nombre.name) },
            singleLine = true,
            maxLines = 1,
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        )
    }
//        Campo de nacionalidad y edad
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        Column(
//            modifier = Modifier.width(200.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = nacionalidad.value,
                onValueChange = nacionalidad.onChange,
                label = { Text(text = nacionalidad.name) },
                singleLine = true,
                maxLines = 1,
                modifier = Modifier
                    .width(220.dp)
                    .padding(start = 5.dp, bottom = 5.dp, end = 2.dp)
            )
        }
        Column(
//            modifier = Modifier.width(200.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = edad.value,
                onValueChange = edad.onChange,
                label = { Text(text = edad.name) },
                singleLine = true,
                maxLines = 1,
                modifier = Modifier
                    .width(220.dp)
                    .padding(start = 2.dp, end = 5.dp, bottom = 5.dp)
            )
        }
    }
//        Campo estado civl, leeyEscribe y ocupación
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Column(
//            modifier = Modifier.width(150.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            estadoCivil(estadoCivil)
        }
        Column(
//            modifier = Modifier.width(150.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = ocupacion.value,
                onValueChange = ocupacion.onChange,
                label = { Text(text = ocupacion.name) },
                singleLine = true,
                maxLines = 1,
                modifier = Modifier
                    .width(180.dp)
                    .padding(start = 2.dp, end = 2.dp, bottom = 5.dp)
            )
        }
        Column(
            modifier = Modifier.width(100.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column {
                Checkbox(
                    checked = sabeLeerYEscribir.value,
                    onCheckedChange = sabeLeerYEscribir.onChange,
                    modifier = Modifier.padding(start = 10.dp, end = 5.dp)
                )
                Text(
                    text = sabeLeerYEscribir.name,
                    modifier = Modifier.padding(start = 2.dp, end = 5.dp, bottom = 2.dp),
                    fontSize = 10.sp
                )
            }
        }

    }
//        Campo domicilio
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        TextField(
            value = domicilio.value,
            onValueChange = domicilio.onChange,
            label = { Text(text = domicilio.name) },
            singleLine = true,
            maxLines = 1,
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        )
    }
//        Campo dni
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        TextField(
            value = dni.value,
            onValueChange = dni.onChange,
            label = { Text(text = dni.name) },
            singleLine = true,
            maxLines = 1,
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        )
    }
//        Campo fechaNacimiento y lugarNacimiento
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        Column(
//            modifier = Modifier.width(200.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            fechaNacimiento(fechaNacimiento, context, viewModel)


        }
        Column(
//            modifier = Modifier.width(200.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = lugarNacimiento.value,
                onValueChange = lugarNacimiento.onChange,
                label = { Text(text = lugarNacimiento.name) },
                singleLine = true,
                maxLines = 1,
                modifier = Modifier
                    .width(250.dp)
                    .padding(start = 2.dp, end = 5.dp, bottom = 5.dp)
            )
        }
    }
//        Campo padre y padreVive
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = padre.value,
                onValueChange = padre.onChange,
                label = { Text(text = padre.name) },
                singleLine = true,
                maxLines = 1,
                modifier = Modifier
                    .width(365.dp)
                    .padding(start = 5.dp, bottom = 5.dp, end = 5.dp)
            )
        }
        Column(
//            modifier = Modifier.width(100.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row {
                Checkbox(
                    checked = padreVive.value,
                    onCheckedChange = padreVive.onChange,
                    modifier = Modifier.width(30.dp)
                )
                Text(
                    text = padreVive.name,
                    textAlign = TextAlign.Start,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(top = 15.dp, end = 5.dp, bottom = 2.dp)
                )
            }

        }
    }
//        Campo madre y madreVive
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        Column(
//            modifier = Modifier.width(300.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = madre.value,
                onValueChange = madre.onChange,
                label = { Text(text = madre.name) },
                singleLine = true,
                maxLines = 1,
                modifier = Modifier
                    .width(365.dp)
                    .padding(start = 5.dp, bottom = 5.dp, end = 5.dp)
            )
        }
        Column(
//            modifier = Modifier.width(100.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row {
                Checkbox(
                    checked = madreVive.value,
                    onCheckedChange = madreVive.onChange,
                    modifier = Modifier.width(30.dp)
                )
                Text(
                    text = madreVive.name,
                    textAlign = TextAlign.Start,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(top = 15.dp, end = 5.dp, bottom = 2.dp)
                )
            }

        }
    }

    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        FilledIconButton(
            onClick = {
                scope.launch(Dispatchers.IO) {
                    viewModel.identificar(context = context)
                }
                navController.popBackStack()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 30.dp, horizontal = 5.dp)
        ) {
            Text(text = "Datos de Identificado")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun fechaNacimiento(fecha: DatosFormulario, context: Context, viewModel: FormularioDatosViewModel) {

    val datePicker = viewModel.datePicker(context)

    Row {
        TextField(
            value = fecha.value,
            onValueChange = { fecha.value = it },
            readOnly = true,
            label = { Text(text = fecha.name) },
            singleLine = true,
            maxLines = 1,
            modifier = Modifier
                .width(250.dp)
                .padding(start = 5.dp, end = 2.dp, bottom = 5.dp),
            trailingIcon = {
                IconButton(
                    onClick = {
                        datePicker.show()
                    }
                ) {
                    Icon(imageVector = Icons.Filled.DateRange, contentDescription = "Fecha")
                }
            }
        )

    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun estadoCivil(estadoCivil: DatosFormulario) {
    val expanded = remember { mutableStateOf(false) }
    val options = listOf("Soltero/a", "Casado/a", "Separado/a", "Divorciado/a", "Viudo/a")

    TextField(
        value = estadoCivil.value,
        onValueChange = estadoCivil.onChange,
        label = { Text(text = estadoCivil.name) },
        readOnly = true,
        singleLine = true,
        maxLines = 1,
        modifier = Modifier
            .width(180.dp)
            .padding(start = 5.dp, bottom = 5.dp),
        trailingIcon = {
            IconButton(onClick = { expanded.value = true }) {
                Icon(Icons.Default.ArrowDropDown, contentDescription = "Localized description")
            }
        }
    )

    DropdownMenu(
        expanded = expanded.value,
        onDismissRequest = { expanded.value = false }
    ) {
        options.forEach {
            DropdownMenuItem(
                text = { Text(it) },
                onClick = {
                    estadoCivil.value = it
                    expanded.value = false
                })
        }
    }

}



