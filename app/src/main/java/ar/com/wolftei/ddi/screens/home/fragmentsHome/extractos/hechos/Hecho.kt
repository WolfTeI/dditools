package ar.com.wolftei.ddi.screens.home.fragmentsHome.extractos.hechos

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import ar.com.wolftei.ddi.composables.fomularios.FormularioCcarHecho

@Composable
fun HechoScreen(navController: NavController){
    val context = LocalContext.current

    FormularioCcarHecho(context = context, navController = navController)
}