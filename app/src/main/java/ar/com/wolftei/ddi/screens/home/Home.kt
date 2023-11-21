package ar.com.wolftei.ddi.screens.home

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import ar.com.wolftei.ddi.composables.DrawerMenu
import ar.com.wolftei.ddi.navigations.AppScreens
import ar.com.wolftei.ddi.navigations.FragmentNavigation
import ar.com.wolftei.ddi.screens.auth.AuthenticationViewModel

@Composable
fun HomeScreen(
    navController: NavController,
    authViewModel: AuthenticationViewModel = hiltViewModel()
    ) {
    if (!authViewModel.isUserLogged()) {
        navController.navigate(AppScreens.Auth.route)
    }

    val navFragController = rememberNavController()
    DrawerMenu(
        titleTop = "Home",
        iconBack = false,
        positionNavigate = 1,
        navigationController = navController,
        navigationFragController = navFragController,
        contenido = { FragmentNavigation(navFragController) }
    )

}