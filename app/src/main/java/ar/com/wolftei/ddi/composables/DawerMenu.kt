package ar.com.wolftei.ddi.composables

import android.content.Context
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.DoorBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material.icons.outlined.DoorBack
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Logout
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import ar.com.wolftei.ddi.DataClass.DatosUser
import ar.com.wolftei.ddi.DataClass.NavItems
import ar.com.wolftei.ddi.data.Firebase.UserGetFirestoreViewModel
import ar.com.wolftei.ddi.navigations.AppScreens
import ar.com.wolftei.ddi.navigations.FragmentSreens
import ar.com.wolftei.ddi.screens.auth.AuthenticationViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerMenu(
    titleTop: String,
    iconBack: Boolean,
    positionNavigate: Int,
    navigationController: NavController,
    navigationFragController: NavController,
    dbUserGet: UserGetFirestoreViewModel = hiltViewModel(),
    authViewModel: AuthenticationViewModel = hiltViewModel(),
    drawerMenuViewModel: DrawerMenuViewModel = hiltViewModel(),
    contenido: @Composable () -> Unit
) {
    val context = LocalContext.current
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    scope.launch(Dispatchers.IO){
        dbUserGet.getUser(authViewModel.userEmail())
        withContext(Dispatchers.Main){
            drawerMenuViewModel.setPerfil(dbUserGet.state.value)
        }
    }

    var selectedItem by remember { mutableIntStateOf(0) }
    val fotoPerfil by drawerMenuViewModel.fotoPerfil.observeAsState(initial = "")
    val nombrePerfil by drawerMenuViewModel.nombrePerfil.observeAsState(initial = "")
    val emailPerfil by drawerMenuViewModel.emailPerfil.observeAsState(initial = "")



    val items = listOf(
        NavItems(
            name = "Configuraciones",
            principalIcon = Icons.Filled.Settings,
            secondaryIcon = Icons.Outlined.Settings,
            route = AppScreens.Config.route
        )
    )


    ModalNavigationDrawer(
        drawerContent = {

            ModalDrawerSheet {
                PhotoProfile(fotoPerfil, context)
                ProfileName(nombrePerfil, emailPerfil)
                Divider(modifier = Modifier.padding(top = 5.dp, bottom = 10.dp))
                items.forEachIndexed { index, item ->
                    NavigationDrawerItem(
                        label = { Text(text = item.name) },
                        selected = selectedItem == index,
                        onClick = {
                            selectedItem = index
                            navigationController.navigate(route = item.route)
                            scope.launch {
                                drawerState.close()
                            }
                        },
                        icon = {
                            if (selectedItem == index) {
                                Icon(item.principalIcon, contentDescription = item.name)
                            } else {
                                Icon(item.secondaryIcon, contentDescription = item.name)
                            }
                        }
                    )
                }
                Divider(modifier = Modifier.padding(top = 10.dp, bottom = 5.dp))
                NavigationDrawerItem(
                    label = { Text(text = "Logout") },
                    selected = false,
                    onClick = {

                        scope.launch {
                            drawerState.close()
                        }
                        authViewModel.signOut()
                        navigationController.popBackStack()
                        navigationController.navigate(AppScreens.Auth.route)

                    },
                    icon = {
                        Icon(Icons.Outlined.Logout, contentDescription = "Logout")
                    }
                )
            }

        },
        drawerState = drawerState,
        modifier = Modifier.fillMaxSize()
    ) {
        Plantilla(
            titleTop = titleTop,
            modifier = Modifier,
            iconBack = iconBack,
            positionNavigate = positionNavigate,
            navigationController = navigationController,
            navigationFragController = navigationFragController,
            drawerState = drawerState
        ) {
            contenido()
        }
    }
}

@Composable
fun PhotoProfile(url: String, context: Context) {
    AsyncImage(
        model = ImageRequest.Builder(context)
            .data(url)
            .transformations(CircleCropTransformation())
            .build(),
        contentDescription = "Logo",
        alignment = Alignment.Center,
        modifier = Modifier
            .width(100.dp)
            .clip(RoundedCornerShape(50))
            .padding(top = 20.dp, bottom = 10.dp, start = 10.dp, end = 10.dp)
    )
}

@Composable
fun ProfileName(name: String, email: String) {
    Text(
        text = name,
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(start = 5.dp)
    )
    Text(
        text = email,
        fontSize = 14.sp,
        color = Color.Gray,
        modifier = Modifier.padding(start = 5.dp)
    )
}