package ar.com.wolftei.ddi.composables

import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.DoorBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Whatsapp
import androidx.compose.material.icons.outlined.CameraAlt
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material.icons.outlined.DoorBack
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Whatsapp
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import ar.com.wolftei.ddi.DataClass.NavItems
import ar.com.wolftei.ddi.navigations.AppScreens
import ar.com.wolftei.ddi.navigations.FragmentSreens
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Plantilla(
    titleTop: String,
    modifier: Modifier,
    iconBack: Boolean,
    positionNavigate: Int,
    navigationController: NavController,
    navigationFragController: NavController,
    drawerState: DrawerState,
    contenido: @Composable () -> Unit
) {

    Scaffold(
        modifier = modifier,
        topBar = { Toolbar(titleTop, iconBack, navigationController, drawerState) },
        bottomBar = { NavigationBar(positionNavigate, navigationController, navigationFragController) },
        content = { contenido() },
    )

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Toolbar(
    title: String,
    iconBack: Boolean,
    navController: NavController,
    drawerState: DrawerState,
) {
    TopAppBar(
        navigationIcon = {
            if (iconBack) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Volver"
                    )
                }

            } else {
                var scope = rememberCoroutineScope()
                IconButton(onClick = {
                    scope.launch {
                        drawerState.open()
                    }
                }) {
                    Icon(
                        imageVector = Icons.Filled.Menu,
                        contentDescription = "Menú"
                    )
                }
            }

        },
        title = { Text(text = title) },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = colorScheme.primary,
            titleContentColor = colorScheme.onPrimary,
            navigationIconContentColor = colorScheme.onPrimary,
            actionIconContentColor = colorScheme.onPrimary,
        )
    )
}

@Composable
fun NavigationBar(position: Int, navController: NavController, navFragController: NavController) {
    var selectedItem by remember { mutableIntStateOf(position) }
    val items = listOf(
        NavItems(
            name = "Incio",
            principalIcon = Icons.Filled.Home,
            secondaryIcon = Icons.Outlined.Home,
            route = FragmentSreens.Inicio.route
        ),
        NavItems(
            name = "Extracto",
            principalIcon = Icons.Filled.Whatsapp,
            secondaryIcon = Icons.Outlined.Whatsapp,
            route = FragmentSreens.Extracto.route
        )
    )

    NavigationBar {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = {
                    if (selectedItem == index) {
                        Icon(item.principalIcon, contentDescription = item.name)
                    } else {
                        Icon(item.secondaryIcon, contentDescription = item.name)
                    }
                },
                label = { Text(item.name) },
                selected = selectedItem == index,
                onClick = {
                    selectedItem = index
                    navFragController.navigate(route = item.route)
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = colorScheme.surfaceVariant,
                    indicatorColor = colorScheme.primary
                )
            )
        }

        NavigationBarItem(
            label = { Text("Camara") },
            selected = selectedItem == 3,
            onClick = {
            selectedItem = 3
            navController.navigate(route = AppScreens.Camara.route)

        }, icon = {
            if (selectedItem == 3) {
                Icon(Icons.Filled.CameraAlt, contentDescription = "Camara")
            } else {
                Icon(Icons.Outlined.CameraAlt, contentDescription = "Camara")
            }
        })
    }
}
