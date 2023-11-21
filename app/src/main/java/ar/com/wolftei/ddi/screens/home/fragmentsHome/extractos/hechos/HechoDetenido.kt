package ar.com.wolftei.ddi.screens.home.fragmentsHome.extractos.hechos

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import ar.com.wolftei.ddi.composables.fomularios.FormularioCcarHechoDetenido

@Composable
fun HechoDetenidoScreen(navController: NavController){
    val context = LocalContext.current
    FormularioCcarHechoDetenido(context = context, navController = navController)
}