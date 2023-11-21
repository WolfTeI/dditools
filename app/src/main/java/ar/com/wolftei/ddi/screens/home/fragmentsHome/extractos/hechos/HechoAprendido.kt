package ar.com.wolftei.ddi.screens.home.fragmentsHome.extractos.hechos

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import ar.com.wolftei.ddi.composables.fomularios.FormularioCcarHechoAprehendido

@Composable
fun HechoAprehendioScreen(navController: NavController){
    val context = LocalContext.current
    FormularioCcarHechoAprehendido(context = context, navController = navController)
}