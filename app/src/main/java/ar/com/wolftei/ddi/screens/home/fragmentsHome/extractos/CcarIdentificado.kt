package ar.com.wolftei.ddi.screens.home.fragmentsHome.extractos

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ar.com.wolftei.ddi.composables.fomularios.FormularoioDatos

@Composable
fun CcarIdentificadoScreen(navController: NavController) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 80.dp),
    ) {
        item {
            val context = LocalContext.current
            FormularoioDatos(context, navController, Modifier)
        }
    }
}