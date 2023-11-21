package ar.com.wolftei.ddi.screens.home.fragmentsHome.extractos.procedimientos

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun CcarProcedimientoScreen(navController: NavController) {
    var tabIndex by remember { mutableStateOf(0) }

    val tabs = listOf("Procedimiento", "Procedimiento con Arehendido", "Procedimiento con Detenido")

    Column(modifier = Modifier.fillMaxWidth().padding(top = 80.dp)) {

        TabRow(selectedTabIndex = tabIndex) {
            tabs.forEachIndexed { index, title ->
                Tab(text = { Text(title) },
                    selected = tabIndex == index,
                    onClick = { tabIndex = index }
                )
            }
        }
        when (tabIndex) {
            0 -> ProcedimientoScreen(navController)
            1 -> ProcedimientoAprehendidoScreen(navController)
            2 -> ProcedimientoDetenidoScreen(navController)
        }
    }
}